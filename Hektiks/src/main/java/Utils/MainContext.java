package Utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebListener
public class MainContext implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        ServletContext sc = sce.getServletContext();
        DataSource ds = null;
        Context initCtx;

        try {

            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/ggwp");
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
