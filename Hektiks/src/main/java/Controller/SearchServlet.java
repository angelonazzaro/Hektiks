package Controller;

import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Utils.Logger.Logger;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static Model.Storage.Entities.GIOCHI;
import static Model.Storage.Entities.GIOCHI_GENERE;

public class SearchServlet extends HttpServlet {

    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.consoleLog(Logger.SERVLET, "SEARCH SERVLET DO GET");

        String search = request.getParameter("q");
        GiocoDAO giocoDAO = new GiocoDAO((DataSource) getServletContext().getAttribute("DataSource"));

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Cerco un qualsiasi gioco che contenga la stringa di ricerca
        // o che appartenga a un genere che contenga la stringa di ricerca
        try {

            List<Gioco> giochi = giocoDAO.doRetrieveByJoin("left",
                    String.format("%s ON %s.codice_gioco = %s.codice_gioco",
                            GIOCHI_GENERE, GIOCHI_GENERE, GIOCHI),
                    String.format("%s.titolo LIKE '%%%s%%' OR %s.codice_gioco LIKE '%%%s%%' OR %s.nome_genere LIKE '%%%s%%'",
                            GIOCHI, search, GIOCHI, search, GIOCHI_GENERE,
                            search));

            out.write(gson.toJson(giochi));

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
