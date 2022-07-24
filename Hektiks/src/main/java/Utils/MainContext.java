package Utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Ogni web application esegue in un contesto:
 * - corrispondenza 1:1 tra web application e il suo contesto
 * - l’interfaccia ServletContext è la vista della Web application (del suo contesto)
 * da parte della Servlet
 *
 * - Si può ottenere un’istanza di tipo ServletContext all’interno della Servlet
 * utilizzando il metodo getServletContext()
 * - Consente di accedere ai parametri di inizializzazione e agli attributi del contesto
 *
 * Nota: il contesto viene condiviso tra tutti gli utenti, le servlet e le richieste
 * della stessa Web application
 **/

@WebListener
public class MainContext implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        ServletContext sc = sce.getServletContext();
        DataSource ds;
        Context initCtx;

        try {

            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/Hektiks");
            sc.setAttribute("DataSource", ds);

        } catch (NamingException e) {

            System.err.println(e.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

        ServletContext cont = sce.getServletContext();
        cont.removeAttribute("DataSource");
    }
}