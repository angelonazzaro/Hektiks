package Controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class HomeServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5982139499022378053L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("title", "Hektiks | Home Page");

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}