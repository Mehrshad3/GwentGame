package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.CommandersHornAbility;
import controller.Checking.WeatherChecking;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.ArrayList;
import java.util.Random;

public class CommanderoftheRedRidersAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
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
