package view.gamegraphics;

import javafx.scene.image.Image;
import model.faction.Card;
import model.faction.Faction;

import java.net.URL;
import java.util.Objects;

public class CardImageLoader {
    public static Image loadImage(Card card) {
        URL url = CardImageLoader.class.getResource(getRelatedPathToCard(card));
        return new Image(Objects.requireNonNull(url).toExternalForm());
    }

    static String getRelatedPathToCard(Card card) {
        Faction faction = card.getCardName().faction;
        String cardName = card.getName().replaceAll(" ", "_");
        return "/IMAGES/" + faction.getName() + "/" + faction.getName() + "_" + cardName.toLowerCase() + ".jpg";
    }
}
