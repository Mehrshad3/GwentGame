package controller.AbilityDoings;

import controller.CardRemoverFromGame;
import controller.Checking.HeroChecking;
import controller.GetRowNumberFromRowName;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.ArrayList;

public class ScorchAbilityDoing extends Ability {
    public ObservableGameStatus game;
    public Card MainCard;

    public ScorchAbilityDoing(String status){
        setStatus(status);
    }
    public ScorchAbilityDoing(){

    }

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public Card getMainCard() {
        return MainCard;
    }

    public ObservableGameStatus getGame() {
        return game;
    }

    public void setGame(ObservableGameStatus game) {
        this.game = game;
    }

    public void DoAbilityOnWholeBoardUnconditionally(){
        ObservableRow[] rows=game.getTable().getRows();
        ArrayList<UnitCard>MaxCards=new ArrayList<>();
        int maxPower = 0;
        for (ObservableRow row : rows) {
            for (Card card : row.getCards()) {
                if (!(card instanceof UnitCard unitCard)) continue;
                if (unitCard.getPower() > maxPower) {
                    maxPower = unitCard.getPower();
                    for (UnitCard card0 : MaxCards) {
                        MaxCards.remove(card0);
                    }
                    MaxCards.add(unitCard);
                } else if (unitCard.getPower() == maxPower) {
                    MaxCards.add(unitCard);
                } else {
                }
            }
        }
        for(UnitCard card0:MaxCards){
            CardRemoverFromGame.Remove(gameStatus,card0);
        }
    }

    public void DoAbilityOnARowUnconditionally(int row) {
        ObservableRow[] rows = game.getTable().getRows();
        ObservableRow wantedrow = rows[row];
        ArrayList<UnitCard> MaxCards = new ArrayList<UnitCard>();
        int maxpower = 0;
        for (Card card : wantedrow.getCards()) {
            if (!(card instanceof UnitCard unitCard)) continue;
            if (unitCard.getPower() > maxpower) {
                maxpower = unitCard.getPower();
                for (UnitCard card0 : MaxCards) {
                    MaxCards.remove(card0);
                }
                MaxCards.add(unitCard);
            } else if (unitCard.getPower() == maxpower) {
                MaxCards.add(unitCard);
            } else {
            }
        }
        for (UnitCard card0 : MaxCards) {
            CardRemoverFromGame.RemoveHavingRow(game, card0, row);
        }
    }

    public void DoAbilityOnARowConditionally(int row) {
        ObservableRow[] rows = game.getTable().getRows();
        ObservableRow wantedrow = rows[row];
        ArrayList<UnitCard> MaxCards = new ArrayList<UnitCard>();
        int maxpower = 0;
        int sumrowpowers = 0;
        for (Card card : wantedrow.getCards()) {
            if (!(card instanceof UnitCard unitCard)) continue;
            if (!HeroChecking.HeroChecking(unitCard)) {
                sumrowpowers = sumrowpowers + unitCard.getPower();
            }
            if (unitCard.getPower() > maxpower) {
                maxpower = unitCard.getPower();
                for (UnitCard card0 : MaxCards) {
                    MaxCards.remove(card0);
                }
                MaxCards.add(unitCard);
            } else if (unitCard.getPower() == maxpower) {
                MaxCards.add(unitCard);
            } else {
            }
        }
        if (sumrowpowers > 9) {
            for (UnitCard card0 : MaxCards) {
                CardRemoverFromGame.RemoveHavingRow(game, card0, row);
            }
        } else {
        }
    }

    @Override
    public void DoCardAbility() {
        switch (status){
            case "general":
                DoAbilityOnWholeBoardUnconditionally();
                break;
            case "opponent:ranged combat":
                int opraco;
                opraco=GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus,"opponent:ranged combat");
                DoAbilityOnARowConditionally(opraco);
                break;
            case "general:opponent":
                if(maincard.getPlayer().equals(maincard.getGameStatus().getPlayer1())){
                    DoAbilityOnARowConditionally(6);
                    DoAbilityOnARowConditionally(5);
                    DoAbilityOnARowConditionally(4);
                }else{
                    DoAbilityOnARowConditionally(1);
                    DoAbilityOnARowConditionally(2);
                    DoAbilityOnARowConditionally(3);
                }
                break;
            case "siege combat:opponent":
                int opsico;
                opsico = GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus,"opponent:siege combat");
                DoAbilityOnARowConditionally(opsico);
                break;
            case "Close combat:oponnent":
                int opclco;
                opclco=GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus,"opponent:close combat");
                DoAbilityOnARowConditionally(opclco);
                break;
            default:

        }
    }

    @Override
    public Ability Copy(Card card) {
        ScorchAbilityDoing scorchAbilityDoing=new ScorchAbilityDoing();
        scorchAbilityDoing.setmaincard(card);
        scorchAbilityDoing.setStatus(status);
        scorchAbilityDoing.setGame(game);
        return scorchAbilityDoing;
    }
}
