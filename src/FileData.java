import java.io.File;

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
}
