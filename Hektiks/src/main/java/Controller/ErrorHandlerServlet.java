package Controller;

import Utils.Logger.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

public class ErrorHandlerServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5982139399022378053L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logger.consoleLog(Logger.INFO, "ERROR HANDLER SERVLET DO GET");
        processError(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logger.consoleLog(Logger.INFO, "ERROR HANDLER SERVLET DO POST");
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Error " + request.getAttribute("jakarta.servlet.error.status_code"));
        request.setAttribute("page", "errors/error.jsp");

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
