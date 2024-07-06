package view.game.graphics;

import enums.card.CardName;
import javafx.scene.image.Image;
import model.faction.Card;
import model.faction.Faction;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CardImageLoader {
    public static Image loadImage(Card card) {
        if (card == null) return null;
        URL url = CardImageLoader.class.getResource(getRelativePathToCard(card.getCardName()));
        return new Image(Objects.requireNonNull(url).toExternalForm());
    }

    static String getRelativePathToCard(CardName cardName) {
        Faction faction = cardName.faction;
        String formattedCardName = cardName.getName().replaceAll(" ", "_");
        String directoryPath = "/IMAGES/" + (cardName.isLeader ? "leaders/" : "") + faction.getName() + "/";
        String rawPath = directoryPath + faction.getName() + "_" + formattedCardName.toLowerCase() + ".jpg";
        return URLDecoder.decode(rawPath, StandardCharsets.UTF_8);
    }
}
