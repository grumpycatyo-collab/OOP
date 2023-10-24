import java.io.File;

public class FileData {
    private String name;
    private long lastModified;
    private long size;

    FileData(File file) {
        this.name = file.getName();
        this.lastModified = file.lastModified();
        this.size = file.length();
    }

    long getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "name='" + name + '\'' +
                ", lastModified=" + lastModified +
                ", size=" + size +
                '}';
    }
}