package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.faction.Faction;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public final class GsonReaderWriter {
    private static final FilePath GWENT_PATH = FilePath.of(".GWENT");
    private static GsonReaderWriter gsonReaderWriter;

    private GsonReaderWriter() {
    }

    public static GsonReaderWriter getGsonReaderWriter() {
        if (gsonReaderWriter == null) gsonReaderWriter = new GsonReaderWriter();
        return gsonReaderWriter;
    }

    private <T extends Serializable> T loadFromFile(File file, Class<T> tClass) {
        Gson gson = new Gson();
        T object = null;
        if (!file.exists()) return null;
        try {
            Scanner scanner = new Scanner(file);
            String json = new String(Files.readAllBytes(file.toPath()));
            JsonReader reader = new JsonReader(new FileReader(file));
            object = gson.fromJson(reader, tClass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    private <T extends Serializable> void saveToFile(T object, FilePath path) {
        File file = getAndCreateFile(path);
        saveToFile(object, file);
    }

    private <T extends Serializable> void saveToFile(T object, File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(object);
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.write(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private <T extends Serializable> File getAndCreateFile(FilePath path) {
        FilePath parentDirectoryPath = path.getParent();
        File parentDirectory = parentDirectoryPath.toFile();
        if (!parentDirectory.mkdirs() && !parentDirectory.exists())
            throw new RuntimeException("Couldn't create necessary directories");
        File file = path.toFile();
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            throw new RuntimeException("File couldn't be created.");
        }
    }

    private <T extends Serializable> T loadFromFile(FilePath relativePathToFile, Class<T> tClass) {
        File file = relativePathToFile.toFile();
        return loadFromFile(file, tClass);
    }

    private FilePath pathOfUser(String username) {
        assert username != null;
        return GWENT_PATH.resolve(FilePath.of("Users/" + username + "/profile.json"));
    }

    /**
     * Detects the current user and returns a path to store the deck for that user.
     */
    private FilePath pathOfDeck(String deckName) {
        User currentUser = User.getCurrentUser();
        assert currentUser != null && deckName != null;
        FilePath relativePath = FilePath.of("Users/" + currentUser.getName() + "/Saved Decks/Deck " + deckName + ".json");
        return GWENT_PATH.resolve(relativePath);

    }

    public User loadUser(String username) {
        assert username != null;
        User user = loadFromFile(pathOfUser(username), User.class);
        if (user == null) return null;
        if (user.getDeck() == null) user.setDeck(new Deck(Faction.MONSTERS));
        return user;
    }

    public boolean doesUserExist(String username) {
        File file = pathOfUser(username).toFile();
        return file.exists();
    }

    public Deck loadDeckByName(String deckName) {
        User currentUser = User.getCurrentUser();
        assert currentUser != null && deckName != null;
        return loadFromFile(pathOfDeck(deckName), Deck.class);
    }

    public Deck loadDeckFromFile(File file) {
        return loadFromFile(file, Deck.class);
    }

    public void saveDeckToFile(Deck deck, File file) {
        saveToFile(deck, file);
    }

    public void saveUser(User user) {
        assert user != null;
        saveToFile(user, pathOfUser(user.getName()));
    }

    public void saveDeckByName(Deck deck, String deckName) {
        assert deck != null && deckName != null;
        saveToFile(deck, pathOfDeck(deckName));
    }
}
