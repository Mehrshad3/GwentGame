package controller.AbilityDoings;

import model.ObservableGameStatus;
import model.faction.Card;

public class TransformerAbilityDoing extends Ability{
    public ObservableGameStatus game;
    public Card MainCard;
    public int numberofAbilityDoingdid=0;
    public String nextcard;

    public String getNextcard() {
        return nextcard;
    }

    public void setNextcard(String nextcard) {
        this.nextcard = nextcard;
    }

    public TransformerAbilityDoing(){

    }

    public TransformerAbilityDoing(String transformto){
        setNextcard(transformto);
    }

    public Card getMainCard() {
        return MainCard;
    }

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public void setGame(ObservableGameStatus game) {
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

    @Override
    public void DoCardAbility() {
        if(numberofAbilityDoingdid==0){
            numberofAbilityDoingdid++;
            gameStatus.getHandleRounds().getNextDoingMethods().add(this);
        }else if(numberofAbilityDoingdid==1){
            DoAbility();
            numberofAbilityDoingdid++;
            game.getHandleRounds().getNextDoingMethods().remove(this);
        }else{

        }
    }

    @Override
    public Ability Copy(Card card) {
        TransformerAbilityDoing transformerAbilityDoing=new TransformerAbilityDoing();
        transformerAbilityDoing.setmaincard(card);
        transformerAbilityDoing.setNextcard(nextcard);
        transformerAbilityDoing.setGame(game);
        transformerAbilityDoing.setStatus(status);
        transformerAbilityDoing.setNumberofAbilityDoingdid(numberofAbilityDoingdid);
        return transformerAbilityDoing;
    }
}
