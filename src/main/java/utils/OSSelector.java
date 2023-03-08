package utils;

public class OSSelector {
    public boolean isWindows() {
        return System.getProperty("os.name").contains("Windows");
    }
    public boolean isLinux() {
        return System.getProperty("os.name").contains("Linux");
    }
}
