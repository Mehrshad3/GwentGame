package controller.AbilityDoings;

import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.Player;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.ArrayList;

public class MusterAbilityDoing extends Ability{
    public ObservableGameStatus  game;
    public Card MainCard;

    public Card getMainCard() {
        return MainCard;
    }

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public ObservableGameStatus getGame() {
        return game;
    }

    public void setGame(ObservableGameStatus game) {
        this.game = game;
    }

    public void DoAbilty(UnitCard card, Player player){
        ArrayList<Card> wantedcards =new ArrayList<>();
        ObservableList<Card>Hand=player.getDeck().getInHandCards();
        ArrayList<Card>Deck=player.getDeck().getCardsInDeck();
        for(Card card0 : Hand){
            if(card.getName().equals(card0.getName())){
                if(wantedcards.contains(card0)){

                }else{
                    wantedcards.add(card0);
                }
            }
            else{}
        }
        for(Card card0 : Deck){
            if(card.getName().equals(card0.getName())){
                if(wantedcards.contains(card0)){

                }else{
                    wantedcards.add(card0);
                }
            }
            else{}
        }

        //TODO:Play wented Cards


    }

    @Override
    public void DoCardAbility() {
        DoAbilty((UnitCard) maincard,player);
        game.getHandleRounds().getNextDoingMethods().remove(this);
    }

    @Override
    public Ability Copy(Card card) {
        MusterAbilityDoing musterAbilityDoing=new MusterAbilityDoing();
        musterAbilityDoing.setmaincard(card);
        musterAbilityDoing.setStatus(status);
        musterAbilityDoing.setGame(game);
        return musterAbilityDoing;
    }
}
