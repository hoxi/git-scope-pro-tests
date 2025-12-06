package net.tagpad.packageA;

public class FileUtils {
    public static String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int lastDot = filename.lastIndexOf('.');
        if (lastDot == -1) {
            return "";
        }
        return filename.substring(lastDot + 1);
    }

    public static String removeExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }
        int lastDot = filename.lastIndexOf('.');
        if (lastDot == -1) {
            return filename;
        }
        return filename.substring(0, lastDot);
    }

    public static boolean isValidFilename(String filename) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }
        String invalidChars = "<>:\"/\\|?*";
        for (char c : invalidChars.toCharArray()) {
            if (filename.indexOf(c) != -1) {
                return false;
            }
        }
        return true;
    }
}
