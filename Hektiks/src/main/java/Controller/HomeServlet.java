package Controller;

import java.io.*;
import java.util.Objects;

import Model.Utente.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import netscape.javascript.JSObject;

public class HomeServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5982139499022378053L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("title", "Hektiks | Home Page");

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String action = (String) request.getParameter("action");
//
//        if (!action.equals("register") && !action.equals("login")) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            request.getRequestDispatcher("/ErrorHandlerServlet").forward(request, response);
//        }
//
//        HttpSession session = request.getSession(false);
//
//        if (session != null && (Utente) session.getAttribute("user") != null) this.doGet(request, response);

    }
}