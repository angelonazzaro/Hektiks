package Utils.Logger;

public class Logger {

    public static String INFO = "INFO";
    public static String WARNING = "WARNING";

    public static void consoleLog(String logType, String message) {
        System.out.println("[" + logType +  "] " + "[" + message +"]");
    }
}
