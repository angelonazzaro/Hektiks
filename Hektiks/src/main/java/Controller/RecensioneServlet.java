package Controller;

import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Prodotto_Ordine.Prodotto_Ordine;
import Model.Prodotto_Ordine.Prodotto_OrdineDAO;
import Model.Recensione.Recensione;
import Model.Recensione.RecensioneDAO;
import Model.Utente.Utente;
import Utils.JSONResponse;
import Utils.Logger.Logger;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import static Model.Storage.Entities.RECENSIONI;
import static Model.Storage.Entities.UTENTI;

public class RecensioneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "RECENSIONE SERVLET DO GET");

        String codice_gioco = request.getParameter("codice_gioco");
        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");

        try {
            Gioco gioco = new GiocoDAO(source).doRetrieveByKey(codice_gioco);

            if (gioco == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.sendRedirect(request.getContextPath() + "/ErrorHandlerServlet");
                return;
            }

            List<Recensione> recensioni = new RecensioneDAO(source).doRetrieveByJoin("inner", String.format("%s ON %s.email = %s.email_utente", UTENTI, UTENTI, RECENSIONI), String.format("%s.codice_gioco = '%s' ORDER BY voto ASC", RECENSIONI, codice_gioco), UTENTI);;

            request.setAttribute("title", gioco.getTitolo() + " | Recensioni");
            request.setAttribute("page", "giochi/recensioni.jsp");
            request.setAttribute("scripts", new String[]{"recensioni.js"});
            request.setAttribute("gioco", gioco);
            request.setAttribute("recensioni", recensioni);


            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "RECENSIONE SERVLET DO POST");

        HttpSession session = request.getSession(false);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        if (session == null || session.getAttribute("user") == null) {
           out.write(gson.toJson(new JSONResponse<String>("error", "Devi accedere per poter lasciare una recensione")));
           return;
        }

        Utente utente = (Utente) session.getAttribute("user");
        String codiceGioco = request.getParameter("codiceGioco");
        double voto = Double.parseDouble(request.getParameter("voto"));
        String descrizione = request.getParameter("descrizione");

        if (codiceGioco == null || voto < 0 || voto > 5) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect(request.getContextPath() + "/ErrorHandlerServlet");
            return;
        }

        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");
        Prodotto_OrdineDAO prodotto_ordineDAO = new Prodotto_OrdineDAO(source);

        try {
            List<Prodotto_Ordine> prodotto_ordine = (List<Prodotto_Ordine>) prodotto_ordineDAO.doRetrieveByCondition("email_utente = '" + utente.getEmail() + "' AND codice_gioco = '" + codiceGioco + "'");

            if (prodotto_ordine == null || prodotto_ordine.size() == 0) {
                out.write(gson.toJson(new JSONResponse<String>("error", "Devi acquistare il gioco per poter lasciare una recensione")));
                return;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        Timestamp date = new Timestamp(System.currentTimeMillis());
        RecensioneDAO recensioneDAO = new RecensioneDAO(source);

        try {

            recensioneDAO.doDelete("email_utente = '" + utente.getEmail() + "' AND codice_gioco = '" + codiceGioco + "'");

            Recensione recensione =  new Recensione();
            recensione.setEmail_utente(utente.getEmail());
            recensione.setCodice_gioco(codiceGioco);
            recensione.setData_ora_pubblicazione(date);
            recensione.setVoto(voto);
            if (descrizione != null)
                recensione.setDescrizione(descrizione);

            recensioneDAO.doSave(recensione);

            out.write(gson.toJson(new JSONResponse<String>("success", "Recensione lasciata!")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
