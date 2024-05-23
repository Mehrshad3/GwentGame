package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public abstract class GsonReaderWriter {
    private static final Path gwentPath = Path.of(".GWENT");

    public static <T extends Serializable> void saveToFile(T object, Path relativePath) {
        File file = getAndCreateFile(object, relativePath);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.write(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static <T extends Serializable> File getAndCreateFile(T object, Path relativePath) {
        Path parentDirectoryPath = relativePath.getParent();
        File parentDirectory = fileOf(parentDirectoryPath);
        if (!parentDirectory.mkdirs() && !parentDirectory.exists())
            throw new RuntimeException("Couldn't create necessary directories");
        File file = fileOf(relativePath);
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            throw new RuntimeException("File couldn't be created.");
        }
    }

    public static <T> T loadFromFile(Path relativePathToFile, Class<T> tClass) {
        Gson gson = new Gson();
        T object = null;
        File file = fileOf(relativePathToFile);
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

    public static File fileOf(Path path) {
        Path resolvedPath = path != null ? gwentPath.resolve(path) : gwentPath;
        return resolvedPath.toFile();
    }
}
