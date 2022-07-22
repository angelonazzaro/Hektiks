package Utils.Logger;

/*
* Una semplice implementazione di un logger
* */
public class Logger {

    // 2 livelli di log: info, warning
    public static final String INFO = "INFO";
    public static final String WARNING = "WARNING";
    public static final boolean log = true;

    public static void consoleLog(String logType, String message) {

        //stampo solo se l'opzione di logging è attiva
        if (log)
            System.out.println("[" + logType + "] " + "[" + message + "]");
    }
}
