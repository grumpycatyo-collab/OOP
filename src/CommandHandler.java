import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.TimerTask;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class CommandHandler {
    private static final String DIRECTORY_PATH = "pika";
    private LocalDateTime snapshotTime;
    private Map<String, FileData> fileDataMap = new HashMap<>();
    private static WatchService watchService;;

    private LocalDateTime SnapShotTime;

    public LocalDateTime getSnapShotTime() {
        return SnapShotTime;
    }

    public void setSnapShotTime(LocalDateTime snapShotTime) {
        SnapShotTime = snapShotTime;
    }

    private static final Logger LOGGER = Logger.getLogger(CommandHandler.class.getName());

    static {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(DIRECTORY_PATH);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handlePikaCommand(String command) {
        if (command.equals("commit")) {
            commit();
        } else if (command.startsWith("info")) {
            String filename = command.substring(5);
            info(filename);
        } else if (command.equals("status")) {
            status();
        } else if (command.equals("help")){
           help();
        }
    }

    private void commit() {
        snapshotTime = LocalDateTime.now();
        setSnapShotTime(snapshotTime);
        fileDataMap.clear();

        try {
            Files.walk(Paths.get(DIRECTORY_PATH)).forEach(path -> {
                if (Files.isRegularFile(path)) {
                    fileDataMap.put(path.toString(), new FileData(path.toFile(), "- commited"));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Snapshot updated at " + snapshotTime);
    }


    private void help(){
        System.out.println("Available commands:");
        System.out.println("    commit - Creates a snapshot of the current state of files.");
        System.out.println("    info <filename> - Provides information about a specific file.");
        System.out.println("    status - Displays the current status of files and any recent changes.");
    }

    private void info(String filename) {

        Path fileData = Paths.get(DIRECTORY_PATH).resolve(filename);
        if (!Files.exists(fileData)) {
            System.out.println("File not found");
        } else {
            try {
                BasicFileAttributes attr = Files.readAttributes(fileData, BasicFileAttributes.class);
                System.out.println("File Name: " + filename);


                String extension = filename.substring(filename.lastIndexOf('.') + 1);
                System.out.println("File Extension: " + extension);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());


                System.out.println("Created Time: " + dateFormat.format(new Date(attr.creationTime().toMillis())));
                System.out.println("Last Updated Time: " + dateFormat.format(new Date(attr.lastModifiedTime().toMillis())));


                if (Files.isRegularFile(fileData)) {
                    if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {

                        System.out.println("Image Size: " + getImageSize(fileData));
                    } else if (extension.equalsIgnoreCase("txt")) {

                        System.out.println("Line Count: " + getLineCount(fileData));
                        System.out.println("Word Count: " + getWordCount(fileData));
                        System.out.println("Character Count: " + getCharacterCount(fileData));
                    } else if (extension.equalsIgnoreCase("py") || extension.equalsIgnoreCase("java")) {

                        System.out.println("Line Count: " + getLineCount(fileData));
                        System.out.println("Class Count: " + getClassCount(fileData));
                        System.out.println("Method Count: " + getMethodCount(fileData));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getImageSize(Path file) throws IOException {
        String extension = file.toString().substring(file.toString().lastIndexOf('.') + 1).toLowerCase();
        if (extension.equals("png") || extension.equals("jpg")) {
            int width = 0;
            int height = 0;

            try {
                java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(file.toFile());
                width = img.getWidth();
                height = img.getHeight();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return width + "x" + height;
        }

        return "Not an image file";
    }

    private int getLineCount(Path file) throws IOException {
        return Files.readAllLines(file).size();
    }

    private int getWordCount(Path file) throws IOException {
        return Files.readAllLines(file).stream()
                .mapToInt(line -> line.split("\\s+").length)
                .sum();
    }

    private int getCharacterCount(Path file) throws IOException {
        return (int) Files.lines(file).flatMapToInt(CharSequence::chars).count();
    }

    private int getClassCount(Path file) throws IOException {
        return (int) Files.lines(file)
                .filter(line -> line.matches("\\s*class\\s+\\w+\\s*\\{"))
                .count();
    }

    private int getMethodCount(Path file) throws IOException {
        return (int) Files.lines(file)
                .filter(line -> line.matches("\\s*\\w+\\s+\\w+\\s*\\(.*\\)\\s*\\{"))
                .count();
    }

    private void status() {
        if (snapshotTime != null) {
            System.out.println("Snapshot created at " + snapshotTime);
        }
        if (fileDataMap.isEmpty()) {
            System.out.println("No changes");
        }

        WatchKey key = watchService.poll();
        if (key != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    System.out.println("Events lost, too many changes!");
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                Path fullPath = Paths.get(DIRECTORY_PATH).resolve(fileName);

                String fileNameStr = fullPath.toString();
                if (fileNameStr.endsWith("~")) {
                    continue;
                }
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    fileDataMap.put(fileNameStr, new FileData(fullPath.toFile(), "- created"));
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    fileDataMap.put(fileNameStr, new FileData(fullPath.toFile(), "- deleted"));
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    fileDataMap.put(fileNameStr, new FileData(fullPath.toFile(), "- modified"));
                }
            }
            key.reset();
        }
        for (Map.Entry<String, FileData> entry : fileDataMap.entrySet()) {
            String name = entry.getKey();
            FileData data = entry.getValue();
            long lastModified = data.getLastModified();
            String modificationType = data.getModificationType();

            System.out.println(name + " : " + lastModified + modificationType);
        }
    }


}
