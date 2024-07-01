package controller.AbilityDoings;

import javafx.collections.ObservableList;
import model.GameStatus;
import model.Player;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.ArrayList;

public class MusterAbilityDoing extends Ability{
    public GameStatus game;
    public Card MainCard;

    public Card getMainCard() {
        return MainCard;
    }

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DoAbilty(UnitCard card, Player player){
        ArrayList<Card> wantedcards =new ArrayList<Card>();
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

    }
}
