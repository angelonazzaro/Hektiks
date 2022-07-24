package Utils.Logger;

/*
* Una semplice implementazione di un logger
* */
public class Logger {

    // 2 livelli di log: info, warning, query, servlet, jsp
    public static final String INFO = "INFO";
    public static final String WARNING = "WARNING";
    public static final String QUERY = "QUERY";
    public static final String SERVLET = "SERVLET";
    public static final String JSP = "JSP";
    public static final boolean logInfo = true;
    public static final boolean logWarning = true;
    public static final boolean logQuery = true;
    public static final boolean logServlet = true;
    public static final boolean logJsp = true;

    public static void consoleLog(String logType, String message) {

        //stampo solo se l'opzione di logging è attiva

        switch (logType) {

            case INFO -> {

                if (logInfo)
                    System.out.println("[" + logType + "] " + "[" + message + "]");
            }

            case WARNING -> {

                if (logWarning)
                    System.out.println("[" + logType + "] " + "[" + message + "]");
            }

            case QUERY -> {

                if (logQuery)
                    System.out.println("[" + logType + "] " + "[" + message + "]");
            }

            case SERVLET -> {

                if (logServlet)
                    System.out.println("[" + logType + "] " + "[" + message + "]");
            }

            case JSP -> {

                if (logJsp)
                    System.out.println("[" + logType + "] " + "[" + message + "]");
            }
        }

    }
}
