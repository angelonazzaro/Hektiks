package Utils.Logger;

public class Logger {

    public static final String INFO = "INFO";
    public static final String WARNING = "WARNING";
    public static final boolean log = true;

    public static void consoleLog(String logType, String message) {

        if(log)
            System.out.println("[" + logType +  "] " + "[" + message +"]");
    }
}
