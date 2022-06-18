package Controller;

import Model.Carrello.Carrello;
import Model.Carrello.CarrelloDAO;
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
import java.util.HashMap;

public class CarrelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "CARRELLO SERVLET DO GET");

        request.setAttribute("title", "Hektiks | Carrello");
        request.setAttribute("page", "carrello/carrello.jsp");

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "CARRELLO SERVLET DO POST");

        HttpSession session = request.getSession(true);
        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");

        String codice_gioco = request.getParameter("codice_gioco");
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HashMap<String, Integer> giochiCarrello = new HashMap<>();

        // Se il carrello esiste, lo prendo dalla sessione
        if (session.getAttribute("carrello") != null)
            giochiCarrello = (HashMap<String, Integer>) session.getAttribute("carrello");


        if (session.getAttribute("user") != null) {
            // La sessione esiste e l'utente è loggato

            Utente utente = (Utente) session.getAttribute("user");
            CarrelloDAO carrelloDAO = new CarrelloDAO(source);
            ProdottoDAO prodottoDAO = new ProdottoDAO(source);

            try {
                Carrello carrello = carrelloDAO.doRetrieveByKey(utente.getEmail());

                // Ci sono due scenari: 1) è un nuovo prodotto 2) è un prodotto già presente nel carrello
                // Nello scenario 1 bisogna salvare in db il nuovo prodotto e aggiungerlo al carrello
                // Nello scenario 2 bisogna aggiornare la quantità del prodotto nel carrello e nel db
                Prodotto prodotto = prodottoDAO.doRetrieveByKey(utente.getEmail(), codice_gioco);

                // Scenario 1
                if (prodotto == null) {
                    prodotto = new Prodotto();
                    prodotto.setEmail_utente(utente.getEmail());
                    prodotto.setCodice_gioco(codice_gioco);
                    prodotto.setQuantita_disponibile(quantita);

                    prodottoDAO.doSave(prodotto);
                } else {
                    // Scenario 2
                    prodotto.setQuantita_disponibile(prodotto.getQuantita_disponibile() + quantita);
                    HashMap<String, Integer> map = new HashMap<>();
                    map.put("quantita_disponibile", prodotto.getQuantita_disponibile());

                    prodottoDAO.doUpdate(map, "email_utente = '" + utente.getEmail() + "' AND codice_gioco = '" + codice_gioco + "'");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (giochiCarrello.containsKey(codice_gioco)) {

            giochiCarrello.replace(codice_gioco, giochiCarrello.get(codice_gioco) + quantita);
        } else
            giochiCarrello.put(codice_gioco, quantita);

        int nuova_quantita;
        if (session.getAttribute("quantita_carrello") != null) {

            nuova_quantita = Integer.parseInt(session.getAttribute("quantita_carrello").toString()) + quantita;
        } else {
            nuova_quantita = quantita;
        }

        session.setAttribute("carrello", giochiCarrello);
        session.setAttribute("quantita_carrello", nuova_quantita);
        out.write(gson.toJson(new JSONResponse<String>("numero_giochi", String.valueOf(nuova_quantita))));

    }
}
