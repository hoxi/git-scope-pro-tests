package net.tagpad.packageB;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logInfo(String message) {
        System.out.println("[INFO] " + getCurrentTimestamp() + " - " + message);
    }

    public void logError(String message) {
        System.err.println("[ERROR] " + getCurrentTimestamp() + " - " + message);
    }

    public void logDebug(String message) {
        System.out.println("[DEBUG] " + getCurrentTimestamp() + " - " + message);
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }
}
