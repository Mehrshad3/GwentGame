package view.gamegraphics;

import enums.card.CardName;
import javafx.scene.image.Image;
import model.faction.Card;
import model.faction.Faction;

import java.net.URL;
import java.util.Objects;

public class CardImageLoader {
    public static Image loadImage(Card card) {
        URL url = CardImageLoader.class.getResource(getRelativePathToCard(card.getCardName()));
        return new Image(Objects.requireNonNull(url).toExternalForm());
    }

    static String getRelativePathToCard(CardName cardName) {
        Faction faction = cardName.faction;
        String formattedCardName = cardName.name().replaceAll(" ", "_");
        String directoryPath = "/IMAGES/" + (cardName.isLeader ? "leaders/" : "") + faction.getName() + "/";
        return directoryPath + faction.getName() + "_" + formattedCardName.toLowerCase() + ".jpg";
    }
}
