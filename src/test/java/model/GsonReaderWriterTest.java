package model;

import enums.card.CardName;
import model.faction.Card;
import model.faction.Faction;
import model.faction.LeaderCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Scanner;

public class GsonReaderWriterTest {
    GsonReaderWriter gsonReaderWriter;

    private static String generateRandomName() {
        final int length = 18;
        SecureRandom random = new SecureRandom();
        StringBuilder nameBuilder = new StringBuilder("temp-test-");
        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt('a', 'z');
            nameBuilder.append((char) randomNumber);
        }
        return nameBuilder.toString();
    }

    @Before
    public void setUp() {
        gsonReaderWriter = GsonReaderWriter.getGsonReaderWriter();
    }

    private Deck constructADeckWithoutLeaderInMonsters1() {
        Deck deck = new Deck(Faction.MONSTERS);
        String cards = """
                Toad
                Cockatrice
                Draug
                Berserker
                Decoy
                Torrential rain
                Torrential rain
                Impenetrable fog
                Biting frost
                Earth elemental
                Arachas
                Arachas
                Arachas
                Fiend
                Celaeno harpy
                Decoy
                Decoy
                Impenetrable fog
                Foglet
                Nekker
                Nekker
                Leshen
                """; // It's not bad to save this into a file.
        Scanner scanner = new Scanner(cards);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            Card card = CardName.getCardByName(name);
            deck.addCardToDeck(card);
        }
        return deck;
    }

    @Test
    public void saveDeckToFile_whenNormalDeckWithoutLeaderInRootDirectory_thenSaveWithoutException() throws IOException {
        Deck deck = constructADeckWithoutLeaderInMonsters1();
        String filename = generateRandomName();
        File tempFile = File.createTempFile(filename, ".tmp");
        gsonReaderWriter.saveDeckToFile(deck, tempFile);
        tempFile.deleteOnExit();
    }

    @Test
    public void saveDeckToFile_whenNormalDeckWithLeaderInRootDirectory_thenSaveWithoutException() throws IOException {
        Deck deck = constructADeckWithoutLeaderInMonsters1();
        deck.setCurrentLeaderCard((LeaderCard) CardName.THE_TREACHEROUS.getNewCard());
        String filename = generateRandomName();
        File tempFile = File.createTempFile(filename, ".tmp");
        gsonReaderWriter.saveDeckToFile(deck, tempFile);
        tempFile.deleteOnExit();
    }

    @Test
    public void loadDeckFromFile_whenNormalSavedDeckWithoutLeaderInRootDirectory_thenLoadsCorrectly() throws IOException {
        Deck deck = constructADeckWithoutLeaderInMonsters1();
        testDeckIsLoadedCorrectly(deck);
    }

    @Test
    public void loadDeckFromFile_whenNormalSavedDeckWithLeaderInRootDirectory_thenLoadsCorrectly() throws IOException {
        Deck deck = constructADeckWithoutLeaderInMonsters1();
        deck.setCurrentLeaderCard((LeaderCard) CardName.THE_TREACHEROUS.getNewCard());
        testDeckIsLoadedCorrectly(deck);
    }

    private void testDeckIsLoadedCorrectly(Deck deckToTest) throws IOException {
        String fileName = generateRandomName();
        File tempFile = File.createTempFile(fileName, ".tmp");
        tempFile.deleteOnExit();
        gsonReaderWriter.saveDeckToFile(deckToTest, tempFile);
        Deck deck2 = gsonReaderWriter.loadDeckFromFile(tempFile);
        Assert.assertNotNull(deck2);
        Assert.assertEquals(deckToTest.getDeckAsSerializable(), deck2.getDeckAsSerializable());
    }
}
