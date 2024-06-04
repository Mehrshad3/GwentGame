package model;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilePath {
    private static final Pattern delimiter = Pattern.compile("[\\\\/]");
    private final Pattern isAbsolute = Pattern.compile("^([A-Z]:)\\\\");
    private final Pattern lastDirectoryPattern = Pattern.compile("\\\\[^<>:\"/\\\\|?*\\x00-\\x1F]++$");
    private final String path;

    private FilePath(String path) {
        if (path == null || path.isEmpty()) this.path = null;
        else {
            Matcher matcher = delimiter.matcher(path);
            path = matcher.replaceAll("\\\\");
            if (path.endsWith("\\")) path = chopString(path);
            this.path = path;
        }
    }

    public static FilePath of(String path) {
        return new FilePath(path);
    }

    public FilePath resolve(FilePath other) {
        if (this.path == null) return other;
        else if (other == null || other.path == null) return this;
        String path2 = other.path;
        if (path2.startsWith("\\") || path2.startsWith("/")) return other;
        return new FilePath(this.path + "\\" + path2);
    }

    /**
     * Tells whether this path is absolute on Windows FileSystem.
     *
     * <p> An absolute path is complete in that it doesn't need to be combined
     * with other path information in order to locate a file.</p>
     *
     * @return {@code true} if, and only if, this path is absolute
     */
    public boolean isAbsolute() {
        Matcher matcher = isAbsolute.matcher(this.path);
        return matcher.find();
    }

    public FilePath getParent() {
        if (path == null) return this;
        Matcher matcher = lastDirectoryPattern.matcher(this.path);
        String parentPath = matcher.replaceAll("");
        return FilePath.of(parentPath);
    }

    public File toFile() {
        return new File(this.path);
    }

    private String chopString(String string) {
        if (string == null || string.isEmpty()) return string;
        else return string.substring(0, string.length() - 1);
    }
}
