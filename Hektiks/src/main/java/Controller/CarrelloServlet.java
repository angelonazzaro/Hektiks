package Controller;

import Model.Carrello.Carrello;
import Model.Carrello.CarrelloDAO;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Model.Storage.Entities.*;

public class CarrelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "CARRELLO SERVLET DO GET");
        HttpSession session = request.getSession(false);

        // Se l'utente è loggato ma il carrello non esiste, lo creo
        if (session != null && session.getAttribute("user") != null) {

//            try {
//                Utente utente = (Utente) session.getAttribute("user");
//                GiocoDAO giocoDAO = new GiocoDAO((DataSource) getServletContext().getAttribute("DataSource"));
//
//                List<Gioco> giochiCarrello = giocoDAO.doRetrieveByJoin("inner",
//                        String.format("%s ON %s.codice_gioco = %s.codice_gioco JOIN %s ON %s.email_utente = %s.email_utente",
//                                PRODOTTI, GIOCHI, PRODOTTI, CARRELLI, PRODOTTI, CARRELLI),
//                        CARRELLI + ".email_utente = '" + utente.getEmail() + "'", PRODOTTI);
//                //per informazioni dei CARRELLI aggiungere CARRELLI come parametro oltre al parametro PRODOTTO
//                //però a questo punto in home servelt quando si fa il retrieve del numero di prodotti nel carrello
//                //non va più bene una List<Prodotti>
//
//                List<Object> carrello_utente = new ArrayList<>();
//                giochiCarrello.forEach(gioco -> carrello_utente.add(gioco.getJoin()));
//
//                session.setAttribute("carrello", carrello_utente);
//
//                System.out.println(carrello_utente);
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }

        request.setAttribute("title", "Hektiks | Carrello");
        request.setAttribute("page", "carrello/carrello.jsp");

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "CARRELLO SERVLET DO POST");

        HttpSession session = request.getSession(false);
        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");
        GiocoDAO giocoDAO = new GiocoDAO(source);

        String codice_gioco = request.getParameter("codice_gioco");
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<Gioco> giochiCarrello = new ArrayList<>();

        // Se non esiste una sessione (utente non loggato) la creo
        if (session == null) {
            session = request.getSession(true);
        } else if (session.getAttribute("carrello") != null) {
            giochiCarrello = (List<Gioco>) session.getAttribute("carrello");
        }

        if (session.getAttribute("user") != null) {
            // La sessione esiste e l'utente è loggato

            Utente utente = (Utente) session.getAttribute("user");
            CarrelloDAO carrelloDAO = new CarrelloDAO(source);
            ProdottoDAO prodottoDAO = new ProdottoDAO(source);

            try {
                Carrello carrello = carrelloDAO.doRetrieveByKey(utente.getEmail());

                // Se il carrello non esiste, lo creo
                if (carrello == null) {
                    carrello = new Carrello();
                    carrello.setEmail_utente(utente.getEmail());
                    carrello.setData_creazione(new Date(System.currentTimeMillis()));
                    carrello.setData_modifica(new Date(System.currentTimeMillis()));
                    carrelloDAO.doSave(carrello);
                }

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
                throw new RuntimeException(e);
            }

        }

        try {
            giochiCarrello.add(giocoDAO.doRetrieveByKey(codice_gioco));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        session.setAttribute("carrello", giochiCarrello);
        out.write(gson.toJson(new JSONResponse<String>("numero_giochi", String.valueOf(giochiCarrello.size()))));
    }
}
