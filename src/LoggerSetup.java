import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerSetup {
    private static FileHandler fileHandler;

    public static Logger getLogger(String className) {
        Logger logger = Logger.getLogger(className);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        if (fileHandler == null) {
            try {
                File logFile = new File("logfile.log");
                boolean append = logFile.exists();
                fileHandler = new FileHandler("logfile.log", append);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return logger;
    }
}