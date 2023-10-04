package logger;

import java.io.IOException;
import java.util.logging.*;

public class DefaultLogger {
    private final static Logger INFO_LOGGER = Logger.getLogger("InfoLogger");
    private final static Logger ERROR_LOGGER = Logger.getLogger("ErrorLogger");

    static {
        configureLogger(INFO_LOGGER, Level.INFO, "info.log");
        configureLogger(ERROR_LOGGER, Level.SEVERE, "error.log");
    }

    private static void configureLogger(Logger logger, Level level, String fileName) {
        logger.setLevel(level);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);

        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler(fileName);
            fileHandler.setLevel(level);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logInfo(String message) {
        INFO_LOGGER.log(Level.INFO, message);
    }

    public void logError(String message) {
        ERROR_LOGGER.log(Level.SEVERE, message);
    }
}

