package model.typeadapters;

import com.google.gson.*;
import controller.AbilityDoings.Ability;
import enums.card.CardName;
import model.App;
import model.faction.Card;
import model.faction.LeaderCard;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.logging.Level;

public class CardTypeAdapter implements JsonDeserializer<Card> {
    private static CardTypeAdapter instance;

    private CardTypeAdapter() {
    }

    public static CardTypeAdapter getInstance() {
        if (instance == null) instance = new CardTypeAdapter();
        return instance;
    }

    @Override
    public Card deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive primitive = (JsonPrimitive) jsonObject.get("name");
        App.LOGGER.log(Level.FINEST, "Card deserializer is called, and primitive is: " + primitive);
        String cardName = primitive.getAsString();
        Card card = CardName.getCardByName(cardName);
        App.LOGGER.log(Level.FINER, "Card deserializer created card " + card);
        return card;
    }
}
