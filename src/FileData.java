import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class FileData {
    private String name;
    private long lastModified;
    private long size;
    private String modificationType; // New field

    FileData(File file, String modificationType) {
        this.name = file.getName();
        this.lastModified = file.lastModified();
        this.size = file.length();
        this.modificationType = modificationType;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getModificationType() {
        return modificationType;
    }

    long getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", lastModified=" + lastModified +
                ", size=" + size +
                ", modificationType='" + modificationType + '\'';
    }


    public static int getLineCount(Path file) throws IOException {
        return Files.readAllLines(file).size();
    }

    public static int getWordCount(Path file) throws IOException {
        return Files.readAllLines(file).stream()
                .mapToInt(line -> line.split("\\s+").length)
                .sum();
    }

    public static int getCharacterCount(Path file) throws IOException {
        return (int) Files.lines(file).flatMapToInt(CharSequence::chars).count();
    }

    public static int getClassCount(Path file) throws IOException {
        Pattern classPattern = Pattern.compile("\\s*class\\s+(\\w+)");
        long count = Files.lines(file)
                .filter(line -> classPattern.matcher(line).find())
                .count();
        return (int) count;
    }

    public static int getMethodCount(Path file) throws IOException {
        Pattern methodPattern = Pattern.compile("\\s*(void\\s+|\\w+\\s+)\\w+\\s*\\([^)]*\\)\\s*\\{");
        long count = Files.lines(file)
                .filter(line -> methodPattern.matcher(line).find())
                .count();
        return (int) count;
    }

}
