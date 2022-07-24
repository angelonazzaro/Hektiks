package Controller;

import Model.Carrello.Carrello;
import Model.Carrello.CarrelloDAO;
import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Prodotto.Prodotto;
import Model.Prodotto.ProdottoDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
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
import java.util.HashMap;
import java.util.List;

import static Model.Storage.Entities.*;

public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logger.consoleLog(Logger.SERVLET, "HOME SERVLET DO GET");

        request.setAttribute("title", "Hektiks | Home Page");
        GiocoDAO giocoDAO = new GiocoDAO((DataSource) getServletContext().getAttribute("DataSource"));

        try {
            // Recupero giochi del momento e best sellers
            request.setAttribute("giochiDelMomento", giocoDAO.doRetrieveByCondition("TRUE LIMIT 9"));
            request.setAttribute("bestSellers", giocoDAO.doRetrieveByCondition("TRUE ORDER BY " + GIOCHI + ".numero_vendite DESC LIMIT 9"));

        } catch (SQLException e) {

            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logger.consoleLog(Logger.SERVLET, "HOME SERVLET DO POST");

        HttpSession session = request.getSession(false);

        // Se l'utente risulta già loggato, richiamo il doGet

        if (session != null && session.getAttribute("user") != null)
            this.doGet(request, response);

        String action = request.getParameter("action");

        // Se non è stato passato nessun parametro "action", o se è diverso da register e login
        // mando un error 400 come risposta

        if (action == null || (!action.equals("register") && !action.equals("login"))) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect(request.getContextPath() + "/ErrorHandlerServlet");

            return;
        }

        String email = request.getParameter("email"), password = request.getParameter("password");
        UtenteDAO utenteDAO = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        List<Utente> utenti;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Se l'utente sta provando a fare il login, cerco nel database se esiste un match con le credenziali
        // Se esiste, creo una nuova sessione, setto l'utente nella sessione, creo il carrello e invio un messaggio di successo
        // altrimenti gli invio un messaggio di errore

        if (action.equals("login")) {

            try {

                utenti = utenteDAO.doRetrieveByCondition("email='" + email + "' AND password_utente=SHA1('" + password + "')");

                if (utenti.isEmpty()) {

                    out.write(gson.toJson(new JSONResponse<String>("error", "Email o password errati")));

                } else {

                    session = request.getSession();
                    session.setAttribute("user", utenti.get(0));
                    creaCarrello(session, utenti.get(0));
                    out.write(gson.toJson(new JSONResponse<String>("success")));
                }

                out.flush();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        } else {

            // Se l'utente sta provando a fare la registrazione, cerco nel database se esiste un utente con la stessa email
            // Se non esiste, creo l'utente e invio un messaggio di successo altrimenti gli invio un messaggio di errore

            try {

                utenti = utenteDAO.doRetrieveByCondition("email='" + email + "'");

                if (!utenti.isEmpty()) {

                    out.write(gson.toJson(new JSONResponse<String>("danger", "Email già registrata")));
                    out.flush();

                    return;
                }

                String nome = request.getParameter("nome"), cognome = request.getParameter("cognome");
                String username;

                //se non è vuota allora lo username non è disponibile
                int offset = 1;
                username = nome.toLowerCase() + "" + cognome.toLowerCase();
                utenti = utenteDAO.doRetrieveByCondition("username LIKE '" + username + "%'");

                if (!utenti.isEmpty()) {

                    String lastUsername = utenti.get(utenti.size() - 1).getUsername();

                    // In questa maniera so quante cifre ci sono nell'ultimo username disponibile e faccio il substring per
                    // prendermi il numero

                    int digits = 0;

                    // generazine username con offset
                    // esempio: nome = "mario" e cognome = "rossi"
                    // username = "mariorossi" se non è presente nessun altro utente con username "mariorossi"
                    // altrimenti username = "mariorossi1" se non è presente nessun altro utente con username "mariorossi1"
                    // il processo si ripete fino a quando non trovo un username disponibile

                    for (int i = 0; i < lastUsername.length(); i++)

                        if (Character.isDigit(lastUsername.charAt(i)))
                            digits++;

                    if (digits > 0)
                        offset = Integer.parseInt(lastUsername.substring(lastUsername.length() - digits)) + 1;

                    username = nome.toLowerCase() + "" + cognome.toLowerCase() + "" + offset;
                }

                // salvo l'utente nel database

                Utente utente = new Utente();
                utente.setEmail(email);
                utente.setUsername(username);
                utente.setNome(nome);
                utente.setCognome(cognome);
                utente.setPassword_utente(password);
                utente.setData_registrazione(new Date(System.currentTimeMillis()));

                if (utenteDAO.doSave(utente))
                    out.write(gson.toJson(new JSONResponse<String>("success", "Registrazione avvenuta con successo!")));

                else
                    out.write(gson.toJson(new JSONResponse<String>("error", "Qualcosa è andato storto :(")));

                out.flush();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    private void creaCarrello(HttpSession session, Utente utente) throws SQLException {

        // vedo se l'utente loggato ha già un carrello

        CarrelloDAO carrelloDAO = new CarrelloDAO((DataSource) getServletContext().getAttribute("DataSource"));

        List<Carrello> carrelli = carrelloDAO.doRetrieveByJoin("inner",
                String.format("%s ON %s.email = %s.email_utente",
                        UTENTI, UTENTI, CARRELLI),
                UTENTI + ".email = '" + utente.getEmail() + "'",
                UTENTI);

        // se è vuoto creo il carrello e lo salvo nel database
        if (carrelli.isEmpty()) {

            Carrello carrello = new Carrello();
            carrello.setEmail_utente(utente.getEmail());
            carrello.setData_creazione(new Date(System.currentTimeMillis()));
            carrello.setData_modifica(new Date(System.currentTimeMillis()));
            carrelloDAO = new CarrelloDAO((DataSource) getServletContext().getAttribute("DataSource"));
            carrelloDAO.doSave(carrello);
        }

        // Cerco se l'utente ha già un carrello in database
        GiocoDAO giocoDAO = new GiocoDAO((DataSource) getServletContext().getAttribute("DataSource"));

        List<Gioco> giochiCarrello = giocoDAO.doRetrieveByJoin("inner",
                String.format("%s ON %s.codice_gioco = %s.codice_gioco JOIN %s ON %s.email_utente = %s.email_utente",
                        PRODOTTI, GIOCHI, PRODOTTI, CARRELLI, PRODOTTI, CARRELLI),
                CARRELLI + ".email_utente = '" + utente.getEmail() + "'",
                PRODOTTI);

        int quantita_carrello = 0, quantita_prodotto;
        HashMap<String, Integer> carrello_utente = new HashMap<>();

        for (Gioco gioco : giochiCarrello) {

            quantita_prodotto = 0;

            // scorro tutti i giochi dell'utente e vedo quante volte il gioco è presente nel carrello

            List<Object> appoggio = gioco.getJoin();

            for (Object o : appoggio) {

                if (o instanceof Prodotto) {

                    quantita_carrello += ((Prodotto) o).getQuantita_disponibile();
                    quantita_prodotto = ((Prodotto) o).getQuantita_disponibile();
                }
            }
            carrello_utente.put(gioco.getCodice_gioco(), quantita_prodotto);
        }

        // Uniamo i carrelli dell'utente con quello della sessione

        if (session.getAttribute("carrello") != null) {

            HashMap<String, Integer> carrello_sessione = (HashMap<String, Integer>) session.getAttribute("carrello");
            ProdottoDAO prodottoDAO = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));

            // Key = codice_gioco

            for (String key : carrello_sessione.keySet()) {

                Prodotto prodotto = prodottoDAO.doRetrieveByKey(utente.getEmail(), key);

                // Se il carrello_utente, cioè quello già presente in database, contiene lo stesso gioco del carrello della sessione
                // allora aggiungo la quantità del carrello della sessione al carrello_utente e aggiorno il database
                // altrimenti aggiungo il nuovo gioco al carrello_utente e lo inserisco nel database

                if (carrello_utente.containsKey(key)) {

                    quantita_carrello += carrello_sessione.get(key);
                    carrello_utente.replace(key, carrello_utente.get(key) + carrello_sessione.get(key));
                    prodotto.setQuantita_disponibile(prodotto.getQuantita_disponibile() + carrello_sessione.get(key));

                    HashMap<String, Integer> map = new HashMap<>();
                    map.put("quantita_disponibile", prodotto.getQuantita_disponibile());

                    prodottoDAO.doUpdate(map, "email_utente = '" + utente.getEmail() + "' AND codice_gioco = '" + key + "'");

                } else {

                    quantita_carrello += carrello_sessione.get(key);

                    carrello_utente.put(key, carrello_sessione.get(key));
                    prodotto = new Prodotto();
                    prodotto.setEmail_utente(utente.getEmail());
                    prodotto.setCodice_gioco(key);
                    prodotto.setQuantita_disponibile(carrello_sessione.get(key));

                    prodottoDAO.doSave(prodotto);
                }
            }
        }
        session.setAttribute("carrello", carrello_utente);
        session.setAttribute("quantita_carrello", quantita_carrello);
    }
}
