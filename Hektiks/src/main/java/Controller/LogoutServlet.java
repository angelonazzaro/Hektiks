package Controller;

import Utils.Logger.Logger;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.consoleLog(Logger.SERVLET, "LOGOUT SERVLET DO GET");

        // invalido la sessione ed effettuo il redirect alla home page

        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }
}

