package logger;

import java.io.IOException;
import java.util.logging.*;

public class DefaultLogger {
    private final static Logger INFO_LOGGER = Logger.getLogger("InfoLogger");
    private final static Logger ERROR_LOGGER = Logger.getLogger("ErrorLogger");

    static {
        INFO_LOGGER.setLevel(Level.INFO);
        ERROR_LOGGER.setLevel(Level.SEVERE);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);

        FileHandler infoFileHandler;
        FileHandler errorFileHandler;

        try {
            infoFileHandler = new FileHandler("info.log");
            errorFileHandler = new FileHandler("error.log");

            infoFileHandler.setLevel(Level.INFO);
            errorFileHandler.setLevel(Level.SEVERE);

            infoFileHandler.setFormatter(new SimpleFormatter());
            errorFileHandler.setFormatter(new SimpleFormatter());

            INFO_LOGGER.addHandler(infoFileHandler);
            ERROR_LOGGER.addHandler(errorFileHandler);
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


