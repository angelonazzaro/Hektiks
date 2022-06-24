package Controller;

import Utils.Logger.Logger;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.consoleLog(Logger.INFO, "LOGOUT SERVLET DO GET");

        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.consoleLog(Logger.INFO, "LOGOUT SERVLET DO POST");

        this.doGet(request, response);
    }
}

