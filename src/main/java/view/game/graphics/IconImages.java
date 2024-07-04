package view.game.graphics;

import javafx.scene.image.Image;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IconImages {
    private static IconImages instance;

    private final Map<String, Image> images = new HashMap<>();

    private IconImages() {
        String iconsDirectoryPath = URLDecoder.decode(getClass().getResource("/IMAGES/icons").getFile(), StandardCharsets.UTF_8);
        File file = new File(iconsDirectoryPath);
        for (File file1 : Objects.requireNonNull(file.listFiles())) {
            images.put(file1.getName(), new Image(file1.getAbsolutePath()));
        }
    }

    public static IconImages getInstance() {
        if (instance == null) instance = new IconImages();
        return instance;
    }

    public Image getIconByFilename(String filename) {
        return images.get(filename);
    }
}
