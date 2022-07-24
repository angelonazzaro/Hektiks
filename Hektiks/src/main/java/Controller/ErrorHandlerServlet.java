package Controller;

import Utils.Logger.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorHandlerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logger.consoleLog(Logger.SERVLET, "ERROR HANDLER SERVLET DO GET");
        processError(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logger.consoleLog(Logger.SERVLET, "ERROR HANDLER SERVLET DO POST");
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getAttribute("jakarta.servlet.error.status_code") != null)
            request.setAttribute("title", "Errore " + request.getAttribute("jakarta.servlet.error.status_code"));
        else
            request.setAttribute("title", "Errore");

        request.setAttribute("page", "errors/error.jsp");
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
