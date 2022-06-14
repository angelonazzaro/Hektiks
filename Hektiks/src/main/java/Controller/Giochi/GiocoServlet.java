package Controller.Giochi;

import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Gioco_Genere.Gioco_Genere;
import Model.Gioco_Genere.Gioco_GenereDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class GiocoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceGioco = request.getParameter("codice_gioco");

        if (codiceGioco == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.sendRedirect("/ErrorHandlerServlet");
        }

        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");
        GiocoDAO giocoDAO = new GiocoDAO(source);
        try {
            Gioco gioco = giocoDAO.doRetrieveByKey(codiceGioco);

            if (gioco == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.getRequestDispatcher("/ErrorHandlerServlet").forward(request, response);
            }

            List<Gioco_Genere> generi = new Gioco_GenereDAO(source).doRetrieveByCondition("codice_gioco = '" + codiceGioco + "'");

            request.setAttribute("gioco", gioco);
            request.setAttribute("generi", generi);
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
