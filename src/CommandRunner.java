import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandRunner extends Thread{
    private static final String DIRECTORY_PATH = "pika";
    private static LocalDateTime snapshotTime;
    private static Map<String, FileData> fileDataMap = new HashMap<>();
    private static WatchService watchService;

    private static String Message;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static LocalDateTime SnapShotTime;

    public static String getMessage() {
        return Message;
    }

    public static void setMessage(String message) {
        Message = message;
    }

    public LocalDateTime getSnapShotTime() {
        return SnapShotTime;
    }


    public static void setSnapShotTime(LocalDateTime snapShotTime) {
        SnapShotTime = snapShotTime;
    }


    static {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(DIRECTORY_PATH);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CommandRunner() {

    }

    @Override
    public void run() {
        // Schedule the detection flow every 5 seconds
        while (true) {
            runDetection();
            try {
                Thread.sleep(5000); // Sleep for 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle the interruption if needed
            }
        }
    }
    private void runDetection() {
        status_log();
    }


    public static void commit(String secondary, String message) {
        Logger logger = LoggerSetup.getLogger(CommandRunner.class.getName());
        logger.setUseParentHandlers(false);
        snapshotTime = LocalDateTime.now();
        setSnapShotTime(snapshotTime);
        if (Objects.equals(secondary, "-m")) {
            setMessage(message);
            System.out.println("Snapshot updated at " + snapshotTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " with message: " + message);
            logger.info("Snapshot updated at " + snapshotTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " with message: " + message);
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
        } else {
            System.out.println("Message not found");
        }

    }

    public static void help() {
        System.out.println("NOTE: ./pika directory is the test directory where files are being monitored)");
        System.out.println("Available commands:");
        System.out.println("    commit - Creates a snapshot of the current state of files.");
        System.out.println("    info <filename> - Provides information about a specific file.");
        System.out.println("    status - Displays the current status of files and any recent changes.");
    }

    public static void info(String filename) {

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

                        System.out.println("Line Count: " + FileData.getLineCount(fileData));
                        System.out.println("Word Count: " + FileData.getWordCount(fileData));
                        System.out.println("Character Count: " + FileData.getCharacterCount(fileData));
                    } else if (extension.equalsIgnoreCase("py") || extension.equalsIgnoreCase("java")) {

                        System.out.println("Line Count: " + FileData.getLineCount(fileData));
                        System.out.println("Class Count: " + FileData.getClassCount(fileData));
                        System.out.println("Method Count: " + FileData.getMethodCount(fileData));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getImageSize(Path file) throws IOException {
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


    public static void status() {
        Logger logger = LoggerSetup.getLogger(CommandRunner.class.getName());
        logger.setUseParentHandlers(false);

        try {
            if (snapshotTime != null && getMessage() != null) {
                String snapshotMessage = "Snapshot created at " + snapshotTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " with message:" + getMessage();
                System.out.println(snapshotMessage);
                logger.info(snapshotMessage);
            }
            if (fileDataMap.isEmpty()) {
                String noChangesMessage = "No changes";
                System.out.println(noChangesMessage);
                logger.info(noChangesMessage);
            }

            WatchKey key = watchService.poll();
            if (key != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        String overflowMessage = "Events lost, too many changes!";
                        System.out.println(overflowMessage);
                        logger.info(overflowMessage);
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

                String logMessage = name + " : " + lastModified + modificationType;
                System.out.println(logMessage);

            }
        } catch (Exception e) {
            System.out.println("An error occurred in status() method");
        }
    }

    private void status_log() {
        Logger logger = LoggerSetup.getLogger(CommandRunner.class.getName());
        logger.setUseParentHandlers(false);

        try {
            if (fileDataMap.isEmpty()) {
                String noChangesMessage = "No changes";
                logger.info(noChangesMessage);
            }

            WatchKey key = watchService.poll();
            if (key != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        String overflowMessage = "Events lost, too many changes!";

                        logger.info(overflowMessage);
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
                        fileDataMap.put(fileNameStr, new FileData(fullPath.toFile(), " - created"));
                        System.out.println(fileNameStr + " - created ");
                        logger.info(fileNameStr + " - created ");
                    } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        fileDataMap.put(fileNameStr, new FileData(fullPath.toFile(), " - deleted"));
                        System.out.println(fileNameStr + " - deleted ");
                        logger.info(fileNameStr + " - deleted ");
                    } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        fileDataMap.put(fileNameStr, new FileData(fullPath.toFile(), " - modified"));
                        System.out.println(fileNameStr + " - modified ");
                        logger.info(fileNameStr + " - modified ");
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred in status() method", e);
        }
    }


}

