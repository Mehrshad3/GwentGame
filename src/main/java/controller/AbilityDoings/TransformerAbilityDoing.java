package controller.AbilityDoings;

import model.GameStatus;
import model.faction.Card;

public class TransformerAbilityDoing {
    public GameStatus game;
    public Card MainCard;
    public int numberofAbilityDoingdid=0;

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

    public int getNumberofAbilityDoingdid() {
        return numberofAbilityDoingdid;
    }

    public void setNumberofAbilityDoingdid(int numberofAbilityDoingdid) {
        this.numberofAbilityDoingdid = numberofAbilityDoingdid;
    }

    public void DoAbility(){
        //TODO
    }

}
