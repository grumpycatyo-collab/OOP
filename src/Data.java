import java.io.File;

public class Data {
    private String name;
    private long lastModified;
    private long size;
    private String modificationType;

    public Data(File file, String modificationType) {
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

    public long getLastModified() {
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
