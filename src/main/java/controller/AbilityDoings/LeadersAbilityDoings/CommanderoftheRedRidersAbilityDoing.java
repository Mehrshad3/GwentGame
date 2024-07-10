package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.Checking.WeatherChecking;
import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.faction.Card;

import java.util.ArrayList;
import java.util.Random;

public class CommanderoftheRedRidersAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void DoCardAbility() {
        ObservableList<Card> hand = player.getDeck().getInHandCards();
        ArrayList<Card> weathercards = new ArrayList<Card>();
        for (Card card : hand) {
            if (WeatherChecking.weatherchecking(card)) {
                weathercards.add(card);
            } else {

            }
        }
        Random random = new Random();
        if (weathercards.isEmpty()) {

        } else {
            Card randomweathercard=weathercards.get(random.nextInt(weathercards.size()));
        }
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        CommanderoftheRedRidersAbilityDoing abilityDoing=new CommanderoftheRedRidersAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setStatus(status);
        abilityDoing.setPlayer(player);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
