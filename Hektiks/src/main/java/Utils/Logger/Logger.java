package Utils.Logger;

public class Logger {

    public static final String INFO = "INFO";
    public static final String WARNING = "WARNING";

    public static void consoleLog(String logType, String message) {
        System.out.println("[" + logType +  "] " + "[" + message +"]");
    }
}
