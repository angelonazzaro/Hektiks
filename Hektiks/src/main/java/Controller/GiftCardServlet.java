package Controller;

import Model.GiftCard.GiftCard;
import Model.GiftCard.GiftCardDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import Utils.JSONResponse;
import Utils.Logger.Logger;
import com.google.gson.Gson;
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

import static Model.Storage.Entities.GIFTCARDS;

public class GiftCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.consoleLog(Logger.SERVLET, "GIFTCARD SERVLET DO POST");

        //recupero il codice giftcard
        String codice_giftCard = request.getParameter("codice_giftcard");

        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");

        GiftCardDAO giftCardDAO = new GiftCardDAO(source);
        List<GiftCard> giftCards;

        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String response_type, response_value;

        try {

            //prima query per controllare se la giftcard esiste
            giftCards = giftCardDAO.doRetrieveByCondition(GIFTCARDS + ".codice_giftCard = '" + codice_giftCard + "'");

            if (giftCards.isEmpty()) {

                response_type = "error";
                response_value = "Giftcard non esistente";

            } else {

                //seconda query, se la giftcard esiste controllo se è stata già riscattata
                giftCards = giftCardDAO.doRetrieveByCondition(GIFTCARDS + ".codice_giftCard = '" + codice_giftCard + "'" +
                        " AND " + GIFTCARDS + ".data_ora_utilizzo IS NULL AND " + GIFTCARDS + ".email_utente IS NULL");

                if (giftCards.isEmpty()) {

                    response_type = "error";
                    response_value = "Giftcard già utilizzata";

                } else {

                    //giftcard non utilizzata, riscatto la giftcard

                    HttpSession session = request.getSession();
                    Utente utente = (Utente) session.getAttribute("user");
                    UtenteDAO utenteDAO = new UtenteDAO(source);

                    //aggiorno il saldo dell'utente
                    utente.setSaldo(utente.getSaldo() + giftCards.get(0).getImporto());
                    session.setAttribute("user", utente);

                    //aggiorno la giftcard, è stata riscattata
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("email_utente", utente.getEmail());
                    map.put("data_ora_utilizzo", new Timestamp(System.currentTimeMillis()).toString());
                    giftCardDAO.doUpdate(map, GIFTCARDS + ".codice_giftCard = '" + codice_giftCard + "'");

                    //aggiorno il saldo dell'utente
                    HashMap<String, Object> map_utente = new HashMap<>();
                    map_utente.put("saldo", utente.getSaldo());
                    utenteDAO.doUpdate(map_utente, "email = '" + utente.getEmail() + "'");

                    response_type = "success";
                    response_value = "Giftcard riscattata con successo";
                }
            }
        } catch (SQLException e) {

            response_type = "error";
            response_value = "Qualcosa è andato storto :(";
            e.printStackTrace();
        }
        out.write(gson.toJson(new JSONResponse<String>(response_type, response_value)));
    }
}
