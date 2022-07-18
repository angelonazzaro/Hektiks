package Controller;

import Model.Genere.GenereDAO;
import Model.GiftCard.GiftCard;
import Model.GiftCard.GiftCardDAO;
import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Gioco_Genere.Gioco_Genere;
import Model.Gioco_Genere.Gioco_GenereDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import Utils.Logger.Logger;
import Utils.LoginChecker;
import Utils.PasswordEncrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdminServlet extends HttpServlet implements LoginChecker {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "ADMIN SERVLET DO GET");

        if (!controllaSeLoggato(request, response, "", true)) return;

        String part = request.getParameter("part"), partToView = "";
        String action = request.getParameter("action");
        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");

        HttpSession session = request.getSession(false);
        Utente utente = (Utente) session.getAttribute("user");

        // Se parts è nullo o uguale ad utenti mostro la parte degli utenti
        if (part == null || part.equals("utenti")) {
            UtenteDAO utenteDAO = new UtenteDAO(source);

            // Se l'azione è nulla, mostro la pagina con tutti gli utenti, altrimenti il form di modifica
            try {

                if (action == null) {

                    request.setAttribute("utenti", utenteDAO.doRetrieveByCondition("email <> '" + utente.getEmail() + "' AND ruolo = 0"));
                    partToView = "parts/utenti.jsp";

                } else if (action.equals("edit")) {

                    String id = request.getParameter("id");
                    // Se l'id è nullo, mando un mesasggio di errore, altrimenti mostro il form di modifica,
                    // Ragionamento analogo nel caso in cui l'utente non esista
                    if (id == null || id.equals("")) {

                        session.setAttribute("msg-error", "Errore nella richiesta");
                        partToView = "parts/utenti.jsp";

                    } else {
                        List<Utente> utenti = utenteDAO.doRetrieveByCondition("username = '" + id + "'");

                        if (utenti == null || utenti.size() == 0) {
                            session.setAttribute("msg-error", "L'utente non esiste");
                            response.sendRedirect(request.getContextPath() + "/admin?part=utenti");
                            return;

                        } else {

                            request.setAttribute("utente", utenti.get(0));
                            partToView = "parts/utente_edit.jsp";
                        }
                    }

                } else {

                    session.setAttribute("msg-error", "Errore nella richiesta");
                    partToView = "parts/utenti.jsp";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (part.equals("prodotti")) {

            GiocoDAO giocoDAO = new GiocoDAO(source);
            GenereDAO genereDAO = new GenereDAO(source);
            Gioco_GenereDAO gioco_genereDAO = new Gioco_GenereDAO(source);

            // Se l'azione è nulla, mostro la pagina con tutti i giochi, altrimenti il form di modifica
            try {

                if (action == null) {
                    request.setAttribute("giochi", giocoDAO.doRetrieveAll());
                    partToView = "parts/prodotti.jsp";

                } else if (action.equals("add")) {

                    request.setAttribute("generi", genereDAO.doRetrieveAll());
                    partToView = "parts/prodotto.jsp";
                } else if (action.equals("edit")) {

                    String id = request.getParameter("id");
                    // Se l'id è nullo, mando un mesasggio di errore, altrimenti mostro il form di modifica,
                    // Ragionamento analogo nel caso in cui l'utente non esista
                    if (id == null || id.equals("")) {

                        session.setAttribute("msg-error", "Errore nella richiesta");
                        partToView = "parts/prodotti.jsp";
                    } else {

                        // Mi prendo tutti i generi del gioco
                        Gioco gioco = giocoDAO.doRetrieveByKey(id);

                        if (gioco == null) {

                            session.setAttribute("msg-error", "Il gioco non esiste");
                            partToView = "parts/prodotti.jsp";

                        } else {

                            request.setAttribute("generi", genereDAO.doRetrieveAll());
                            request.setAttribute("gioco_generi", gioco_genereDAO.doRetrieveByCondition("codice_gioco = '" + id + "'"));
                            request.setAttribute("gioco", gioco);
                            partToView = "parts/prodotto.jsp";
                        }
                    }

                } else if (action.equals("delete")) {

                    String id = request.getParameter("id");

                    if (id == null || id.equals("")) {

                        session.setAttribute("msg-error", "Errore nella richiesta");
                        partToView = "parts/prodotti.jsp";

                    } else {

                        giocoDAO.doDelete("codice_gioco = '" + id + "'");
                        session.setAttribute("msg-success", "Gioco eliminato");
                        response.sendRedirect(request.getContextPath() + "/admin?part=prodotti");
                        return;
                    }

                } else {

                    session.setAttribute("msg-error", "Errore nella richiesta");
                    partToView = "parts/prodotti.jsp";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (part.equals("giftcards")) {

            GiftCardDAO giftCardDAO = new GiftCardDAO(source);

            // Se l'azione è nulla, mostro la pagina con tutti i giochi, altrimenti il form di modifica
            try {

                if (action == null) {
                    request.setAttribute("giftCards", giftCardDAO.doRetrieveAll());
                    partToView = "parts/giftcards.jsp";

                } else if (action.equals("add")) {

                    partToView = "parts/giftcard.jsp";

                } else {

                    session.setAttribute("msg-error", "Errore nella richiesta");
                    partToView = "parts/giftcards.jsp";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("part", partToView);

        request.setAttribute("title", "Hektiks | Admin");
        request.setAttribute("page", "admin/admin.jsp");
        request.setAttribute("scripts", new String[]{"admin.js"});

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.consoleLog(Logger.INFO, "ADMIN SERVLET DO POST");

        if (!controllaSeLoggato(request, response, "", true)) return;

        String action = request.getParameter("action"), componente = request.getParameter("componente");
        HttpSession session = request.getSession(false);

        if (action == null || componente == null) {
            session.setAttribute("msg-error", "Errore nella richiesta");
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");

        switch (componente) {
            case "utente" -> gestisciUtente(request, response, action, source);
            case "prodotto" -> gestisciProdotto(request, response, action, source);
            case "giftcard" -> gestisciGiftCard(request, response, action, source);
            default -> {
                session.setAttribute("msg-error", "Errore nella richiesta");
                response.sendRedirect(request.getContextPath() + "/admin");
            }
        }
    }

    private void gestisciProdotto(HttpServletRequest request, HttpServletResponse response, String action, DataSource source) throws IOException {

        if (!controllaValiditaCampiGioco(request, action, source)) return;

        String newCodice = request.getParameter("codice");
        String newTitolo = request.getParameter("titolo");
        String newDescrizione = request.getParameter("descrizione");
        String newTrailer = request.getParameter("trailer");
        String newCopertina = request.getParameter("copertina");
        double newPrezzo = Double.parseDouble(request.getParameter("prezzo"));
        int newQuantita = Integer.parseInt(request.getParameter("quantita"));
        String newData_uscita = request.getParameter("data_uscita");
        double newSconto = Double.parseDouble(request.getParameter("sconto"));

        GiocoDAO giocoDAO = new GiocoDAO(source);
        HttpSession session = request.getSession(false);

        if (action.equals("add")) {
            Gioco gioco = new Gioco();

            gioco.setCodice_gioco(newCodice);
            gioco.setTitolo(newTitolo);
            gioco.setDescrizione(newDescrizione);
            gioco.setTrailer(newTrailer);
            gioco.setCopertina(newCopertina);
            gioco.setPrezzo(newPrezzo);
            gioco.setQuantita_disponibile(newQuantita);
            gioco.setData_uscita(Date.valueOf(newData_uscita));
            gioco.setPercentuale_sconto(newSconto);


            try {
                if (giocoDAO.doSave(gioco)) {

                    salvaGeneri(request, newCodice, source, false);

                    session.setAttribute("msg-success", "Gioco aggiunto correttamente!");
                } else
                    session.setAttribute("msg-error", "Qualcosa è andato storto!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("edit")) {

            String currentCode = request.getParameter("current-code");
            String currentTitolo = request.getParameter("current-titolo");
            String currentDescrizione = request.getParameter("current-descrizione");
            String currentTrailer = request.getParameter("current-trailer");
            String currentCopertina = request.getParameter("current-copertina");
            double currentPrezzo = Double.parseDouble(request.getParameter("current-prezzo"));
            int currentQuantita = Integer.parseInt(request.getParameter("current-quantita"));
            String currentData_uscita = request.getParameter("current-data");
            double currentSconto = Double.parseDouble(request.getParameter("current-sconto"));

            HashMap<String, Object> map = new HashMap<>();
            map.put("codice_gioco", newCodice);
            map.put("titolo", newTitolo);
            map.put("descrizione", newDescrizione);
            map.put("trailer", newTrailer);
            map.put("copertina", newCopertina);
            map.put("prezzo", newPrezzo);
            map.put("quantita_disponibile", newQuantita);
            map.put("data_uscita", newData_uscita);
            map.put("percentuale_sconto", newSconto);

            try {
                if (giocoDAO.doUpdate(map, "codice_gioco = '" + currentCode + "'")) {

                    salvaGeneri(request, newCodice, source, true);

                    session.setAttribute("msg-success", "Gioco modificato correttamente!");
                }else
                    session.setAttribute("msg-error", "Qualcosa è andato storto!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin?part=prodotti");
    }

    private void gestisciUtente(HttpServletRequest request, HttpServletResponse response, String action, DataSource source) throws IOException {

        HttpSession session = request.getSession(false);

        if (!action.equals("edit")) {

            session.setAttribute("msg-error", "Errore nella richiesta");
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        String currentUsername = request.getParameter("current-username"), currentEmail = request.getParameter("current-email"), currentPassword = request.getParameter("current-password");

        if (currentEmail == null || currentEmail.equals("")) {

            session.setAttribute("msg-error", "Errore nella richiesta");
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        String newUsername = request.getParameter("username"), newEmail = request.getParameter("email"), newPassword = request.getParameter("password");

        // Encrypta solo se la password non è nulla, altrimenti dopo risulta valida
        if (newPassword != null && !newPassword.equals("")) {
            try {

                newPassword = PasswordEncrypt.sha1(request.getParameter("password"));
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }

        UtenteDAO utenteDAO = new UtenteDAO(source);
        HashMap<String, String> map = new HashMap<>();
        boolean update = false;

        if (newUsername != null && !newUsername.equals("")) {

            if (!controllaValiditaCampiUtente("username", newUsername, currentEmail, utenteDAO)) {

                session.setAttribute("msg-error", "Lo username è già in uso");
                response.sendRedirect(request.getContextPath() + "/admin?part=utenti&action=edit&id=" + currentUsername);
                return;

            } else {

                if(!currentUsername.equals(newUsername)){
                    map.put("username", newUsername);
                    update = true;
                }
            }
        }

        if (newEmail != null && !newEmail.equals("")) {

            if (!controllaValiditaCampiUtente("email", newEmail, currentEmail, utenteDAO)) {

                session.setAttribute("msg-error", "L'email è già in uso");
                response.sendRedirect(request.getContextPath() + "/admin?part=utenti&action=edit&id=" + currentUsername);
                return;
            } else {

                if(!currentEmail.equals(newEmail)){
                    map.put("email", newEmail);
                    update = true;
                }
            }
        }

        if (newPassword != null && !newPassword.equals("")) {

            if (!newPassword.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")) {

                session.setAttribute("msg-error", "La password non rispetta i requisti");
                response.sendRedirect(request.getContextPath() + "/admin?part=utenti&action=edit&id=" + currentUsername);
                return;
            }

            if(!currentPassword.equals(newPassword)){
                map.put("password_utente", newPassword);
                update = true;
            }
        }

        try {

            if(update)
                utenteDAO.doUpdate(map, "email = '" + currentEmail + "'");
            session.setAttribute("msg-success", "Profilo aggiornato con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/admin?part=utenti");
    }

    private void gestisciGiftCard(HttpServletRequest request, HttpServletResponse response, String action, DataSource source) throws IOException {

        HttpSession session = request.getSession(false);

        if (!action.equals("add")) {
            session.setAttribute("msg-error", "Errore nella richiesta");
            response.sendRedirect(request.getContextPath() + "/admin?part=giftcards");
            return;
        }

        String codice = request.getParameter("codice");
        double importo = Double.parseDouble(request.getParameter("importo"));

        if (codice == null || codice.length() != 6 || importo <= 0) {
            session.setAttribute("msg-error", "Errore nella richiesta");
            response.sendRedirect(request.getContextPath() + "/admin?part=giftcards");
            return;
        }

        GiftCardDAO giftCardDAO = new GiftCardDAO(source);

        try {
            if (giftCardDAO.doRetrieveByKey(codice) != null) {
                session.setAttribute("msg-error", "Il codice è già in uso");
                response.sendRedirect(request.getContextPath() + "/admin?part=giftcards");
                return;
            }

            GiftCard giftCard = new GiftCard();
            giftCard.setCodice_giftCard(codice);
            giftCard.setImporto(importo);
            giftCard.setData_ora_creazione(new Timestamp(System.currentTimeMillis()));

            giftCardDAO.doSave(giftCard);
            session.setAttribute("msg-success", "GiftCard aggiunta con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/admin?part=giftcards");
    }

    private void salvaGeneri(HttpServletRequest request, String codice, DataSource source, boolean isEdit) throws SQLException {

        Gioco_GenereDAO gioco_genereDAO = new Gioco_GenereDAO(source);

        if (isEdit)
            gioco_genereDAO.doDelete("codice_gioco = '" + codice + "'");

        String[] generi = request.getParameterValues("generi[]");

        for (String genere : generi) {

            Gioco_Genere gioco_genere = new Gioco_Genere();
            gioco_genere.setCodice_gioco(codice);
            gioco_genere.setNome_genere(genere);

            gioco_genereDAO.doSave(gioco_genere);
        }
    }

    private boolean controllaValiditaCampiUtente(String campo, String parametro, String email, UtenteDAO utenteDAO) {
        // Controllo che lo username non sia già in uso da un altro utente
        List<Utente> utenti;
        try {
            utenti = utenteDAO.doRetrieveByCondition(campo + " = '" + parametro + "' AND email <> '" + email + "'");

            return utenti == null || utenti.size() == 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean controllaValiditaCampiGioco(HttpServletRequest request, String action, DataSource source) {

        String codice = request.getParameter("codice");
        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        String trailer = request.getParameter("trailer");
        String copertina = request.getParameter("copertina");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        String data_uscita = request.getParameter("data_uscita");
        double sconto = Double.parseDouble(request.getParameter("sconto"));

        HttpSession session = request.getSession(false);

        if (codice == null || codice.equals("") || titolo == null || titolo.equals("") || descrizione == null || descrizione.equals("") || data_uscita == null || data_uscita.equals("") || trailer == null || trailer.equals("") || copertina == null || copertina.equals("") || prezzo <= 0 || sconto < 0 || quantita < 0) {
            session.setAttribute("msg-error", "Compila tutti i campi richiesti");
            return false;
        }

        if (action.equals("edit")) {
            String currentCode = request.getParameter("current-code");

            if (currentCode == null || currentCode.equals("")) return false;

            GiocoDAO giocoDAO = new GiocoDAO(source);
            List<Gioco> giochi = null;
            try {
                giochi = giocoDAO.doRetrieveByCondition("codice_gioco = '" + codice + "' AND codice_gioco <> '" + currentCode + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return giochi == null || giochi.size() <= 0;

        } else if (!action.equals("add")) {
            session.setAttribute("msg-error", "Errore nella richiesta");
            return false;
        }

        return true;
    }
}
