package Controller;

import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Prodotto.Prodotto;
import Model.Prodotto.ProdottoDAO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Model.Storage.Entities.PRODOTTI;

public class CarrelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.SERVLET, "CARRELLO SERVLET DO GET");

        HttpSession session = request.getSession(false);

        //se la sessione è presente recupero i giochi nel carrello
        //che saranno mostrati in carrello.jsp

        if (session != null && session.getAttribute("carrello") != null) {

            GiocoDAO giocoDAO = new GiocoDAO((DataSource) getServletContext().getAttribute("DataSource"));
            HashMap<String, Integer> carrello = (HashMap<String, Integer>) session.getAttribute("carrello");
            List<Gioco> giochi = new ArrayList<>();

            for (String codice_gioco : carrello.keySet()) {

                try {

                    giochi.add(giocoDAO.doRetrieveByKey(codice_gioco));

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
            request.setAttribute("giochiCarrello", giochi);
        }
        request.setAttribute("title", "Hektiks | Carrello");
        request.setAttribute("page", "carrello/carrello.jsp");
        request.setAttribute("scripts", new String[]{"cart.js"});

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.consoleLog(Logger.SERVLET, "CARRELLO SERVLET DO POST");

        HttpSession session = request.getSession(true);
        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");

        String codice_gioco = request.getParameter("codice_gioco");
        String action = request.getParameter("action");
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HashMap<String, Integer> giochiCarrello = new HashMap<>();

        // Se il carrello esiste, lo prendo dalla sessione
        if (session.getAttribute("carrello") != null)
            giochiCarrello = (HashMap<String, Integer>) session.getAttribute("carrello");

        // Action = add -> proviene da GiocoServlet -> aggiungo il gioco al carrello e incremento di 1
        // Action = update -> proviene da CarrelloServlet -> aggiorno la quantita del gioco nel carrello
        // Action = remove -> proviene da CarrelloServlet -> tolgo il gioco dal carrello

        if (session.getAttribute("user") != null) {

            // La sessione esiste e l'utente è loggato
            Utente utente = (Utente) session.getAttribute("user");
            ProdottoDAO prodottoDAO = new ProdottoDAO(source);

            try {

                // Ci sono due scenari:
                // 1) è un nuovo prodotto
                // 2) è un prodotto già presente nel carrello
                // Nello scenario 1 bisogna salvare in db il nuovo prodotto e aggiungerlo al carrello
                // Nello scenario 2 bisogna aggiornare la quantità del prodotto nel carrello e nel db

                Prodotto prodotto = prodottoDAO.doRetrieveByKey(utente.getEmail(), codice_gioco);

                // Scenario 1 (comprende sia add che update ma non remove)
                if (prodotto == null) {

                    prodotto = new Prodotto();
                    prodotto.setEmail_utente(utente.getEmail());
                    prodotto.setCodice_gioco(codice_gioco);
                    prodotto.setQuantita_disponibile(quantita);
                    prodottoDAO.doSave(prodotto);

                } else {

                    // Scenario 2
                    int quantita_disponibile = prodotto.getQuantita_disponibile();

                    switch (action) {

                        case "remove" ->
                                prodottoDAO.doDelete(
                                        String.format("%s.email_utente = '%s' AND %s.codice_gioco = '%s'",
                                                PRODOTTI, utente.getEmail(), PRODOTTI, codice_gioco));
                        case "update" -> quantita_disponibile = quantita;
                        case "add" -> quantita_disponibile += quantita;

                    }

                    //agiorno la quantita disponibile nel db

                    prodotto.setQuantita_disponibile(quantita_disponibile);
                    HashMap<String, Integer> map = new HashMap<>();
                    map.put("quantita_disponibile", quantita_disponibile);

                    prodottoDAO.doUpdate(map, "email_utente = '" + utente.getEmail() + "' AND codice_gioco = '" + codice_gioco + "'");
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

        int quantita_rimossa = 0, vecchia_quantita = 0;
        if (action.equals("remove")) {

            // Rimuovo il gioco dal carrello
            quantita_rimossa = giochiCarrello.remove(codice_gioco);

        } else if (action.equals("update") || action.equals("add")) {

            if (giochiCarrello.containsKey(codice_gioco)) {

                if (action.equals("update")) {

                    //recupero la vecchia quantità prima di aggiornare

                    vecchia_quantita = giochiCarrello.get(codice_gioco);
                    giochiCarrello.replace(codice_gioco, quantita);

                } else
                    //aggiorno con la nuova quantità
                    giochiCarrello.replace(codice_gioco, giochiCarrello.get(codice_gioco) + quantita);

            } else

                //se il gioco non è presente lo aggiungo
                giochiCarrello.put(codice_gioco, quantita);
        }

        int nuova_quantita = 0;

        if (session.getAttribute("quantita_carrello") != null) {

            switch (action) {

                case "remove" ->
                        nuova_quantita = Integer.parseInt(session.getAttribute("quantita_carrello").toString()) - quantita_rimossa;
                case "add" ->
                        nuova_quantita = Integer.parseInt(session.getAttribute("quantita_carrello").toString()) + quantita;
                case "update" ->
                        nuova_quantita = Integer.parseInt(session.getAttribute("quantita_carrello").toString()) + (quantita - vecchia_quantita);
            }

        } else
            nuova_quantita = quantita;

        session.setAttribute("carrello", giochiCarrello);
        session.setAttribute("quantita_carrello", nuova_quantita);
        out.write(gson.toJson(new JSONResponse<String>("numero_giochi", String.valueOf(nuova_quantita))));
    }
}
