package Controller;

import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Gioco_Genere.Gioco_Genere;
import Model.Gioco_Genere.Gioco_GenereDAO;
import Utils.Logger.Logger;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GiocoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "GIOCO SERVLET DO GET");

        String codiceGioco = request.getParameter("codice_gioco");
        String action = request.getParameter("action");

        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");
        GiocoDAO giocoDAO = new GiocoDAO(source);

        // Mostra singolo gioco
        if (action == null && codiceGioco != null) {

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
                request.setAttribute("scripts", new String[]{"game.js"});

                request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action != null && action.equals("show-all")) {
            try {
                List<Gioco> giochi = giocoDAO.doRetrieveAll();

                request.setAttribute("giochi", giochi);
                request.setAttribute("title", "Hektiks | Giochi");
                request.setAttribute("page", "giochi/show_all.jsp");

                request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }


    }
}
