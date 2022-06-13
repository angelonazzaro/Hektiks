package Controller.Giochi;

import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

import static Model.Storage.Entities.GIOCHI;
import static Model.Storage.Entities.SCONTI;


public class GiocoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceGioco = request.getParameter("codice_gioco");

        if (codiceGioco == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.sendRedirect("/ErrorHandlerServlet");
        }

        GiocoDAO giocoDAO = new GiocoDAO((DataSource) getServletContext().getAttribute("DataSource"));
        try {
            Gioco gioco = giocoDAO.doRetrieveByJoin("left", SCONTI + " ON " + SCONTI + ".codice_gioco=" + GIOCHI + ".codice_gioco", GIOCHI + ".codice_gioco='" + codiceGioco + "'", SCONTI).get(0) ;

            if (gioco == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.getRequestDispatcher("/ErrorHandlerServlet").forward(request, response);
            }

            request.setAttribute("gioco", gioco);
            request.setAttribute("title", gioco.getTitolo());
            request.setAttribute("page", "giochi/gioco.jsp");

            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
