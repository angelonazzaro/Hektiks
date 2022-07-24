package Controller;

import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Gioco_Genere.Gioco_Genere;
import Model.Gioco_Genere.Gioco_GenereDAO;
import Model.Recensione.Recensione;
import Model.Recensione.RecensioneDAO;
import Utils.Logger.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static Model.Storage.Entities.RECENSIONI;
import static Model.Storage.Entities.UTENTI;

public class GiocoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.SERVLET, "GIOCO SERVLET DO GET");

        String codiceGioco = request.getParameter("codice_gioco");
        String action = request.getParameter("action");

        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");
        GiocoDAO giocoDAO = new GiocoDAO(source);

        // Mostra singolo gioco
        if (action == null && codiceGioco != null) {

            try {

                Gioco gioco = giocoDAO.doRetrieveByKey(codiceGioco);

                //se il gioco non è presente mostra pagina di errore

                if (gioco == null) {

                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    request.getRequestDispatcher("/ErrorHandlerServlet").forward(request, response);
                    return;
                }

                //recupero le recensioni e i generi relativi al gioco

                RecensioneDAO recensioneDAO = new RecensioneDAO(source);
                List<Gioco_Genere> generi = new Gioco_GenereDAO(source).doRetrieveByCondition("codice_gioco = '" + codiceGioco + "'");
                List<Recensione> recensioni = recensioneDAO.doRetrieveByJoin("inner",
                        String.format("%s ON %s.email = %s.email_utente",
                                UTENTI, UTENTI, RECENSIONI),
                        String.format("%s.codice_gioco = '%s' ORDER BY %s.voto",
                                RECENSIONI, codiceGioco, RECENSIONI),
                        UTENTI);

                request.setAttribute("gioco", gioco);
                request.setAttribute("recensioni", recensioni);
                request.setAttribute("generi", generi);
                request.setAttribute("title", gioco.getTitolo());
                request.setAttribute("page", "giochi/gioco.jsp");
                request.setAttribute("scripts", new String[]{"game.js", "recensioni.js"});

                request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

            } catch (SQLException e) {

                e.printStackTrace();
            }

        } else if (action != null && action.equals("show-all")) {

            //action = "show-all" mostra tutti i giochi

            try {

                List<Gioco> giochi = giocoDAO.doRetrieveAll();

                request.setAttribute("giochi", giochi);
                request.setAttribute("title", "Hektiks | Giochi");
                request.setAttribute("page", "giochi/show_all.jsp");

                request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

            } catch (SQLException e) {

                e.printStackTrace();
            }
        } else {

            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
