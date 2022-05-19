package Controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class ErrorHandlerServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5982139399022378053L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processError(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Error " + request.getAttribute("jakarta.servlet.error.status_code"));
        request.setAttribute("page", "errors/error.jsp");

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
