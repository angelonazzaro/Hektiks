package Controller;

import Model.GiftCard.GiftCard;
import Model.GiftCard.GiftCardDAO;
import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import Utils.Logger.Logger;
import Utils.LoginChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
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
                            partToView = "parts/utenti.jsp";
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
                throw new RuntimeException(e);
            }

        } else if (part.equals("prodotti")) {
            GiocoDAO giocoDAO = new GiocoDAO(source);

            // Se l'azione è nulla, mostro la pagina con tutti i giochi, altrimenti il form di modifica
            try {

                if (action == null) {
                    request.setAttribute("giochi", giocoDAO.doRetrieveAll());
                    partToView = "parts/prodotti.jsp";

                } else if (action.equals("add")) {
                    partToView = "parts/prodotto.jsp";
                } else if (action.equals("edit")) {
                    String id = request.getParameter("id");
                    // Se l'id è nullo, mando un mesasggio di errore, altrimenti mostro il form di modifica,
                    // Ragionamento analogo nel caso in cui l'utente non esista
                    if (id == null || id.equals("")) {
                        session.setAttribute("msg-error", "Errore nella richiesta");
                        partToView = "parts/prodotti.jsp";
                    } else {
                        Gioco gioco = giocoDAO.doRetrieveByKey(id);

                        if (gioco == null) {
                            session.setAttribute("msg-error", "Il gioco non esiste");
                            partToView = "parts/prodotti.jsp";
                        } else {
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
                        partToView = "parts/prodotto.jsp";
                    }
                } else {
                    session.setAttribute("msg-error", "Errore nella richiesta");
                    partToView = "parts/prodotti.jsp";
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
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
                throw new RuntimeException(e);
            }
        }

        request.setAttribute("part", partToView);

        request.setAttribute("title", "Hektiks | Admin");
        request.setAttribute("page", "admin/admin.jsp");
        request.setAttribute("scripts", new String[]{"admin.js"});

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger.consoleLog(Logger.INFO, "ADMIN SERVLET DO POST");

        if (!controllaSeLoggato(request, response, "", true)) return;

        String action = request.getParameter("action"), componente = request.getParameter("componente");
        HttpSession session = request.getSession(false);

        System.out.println("ACTION: " + action);
        System.out.println("COMPONENTE: " + componente);

        if (action == null || componente == null) {
            session.setAttribute("msg-error", "Errore nella richiesta");
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");

        if (componente.equals("utente")) {
            gestisciUtente(request, response, action, source);
        }

    }

    private void gestisciUtente(HttpServletRequest request, HttpServletResponse response, String action, DataSource source) throws IOException {

        String currentUsername = request.getParameter("current-username");
        String username = request.getParameter("username"), email = request.getParameter("username"), password = request.getParameter("password");

        HttpSession session = request.getSession(false);

        System.out.println("ACTION: " + action);
        System.out.println("CURRENT USERNAME: " + currentUsername);
        System.out.println("USERNAME: " + username);
        System.out.println("EMAIL: " + email);
        System.out.println("PASSWORD: " + password);

        if (!action.equals("edit")) {
            session.setAttribute("msg-error", "Errore nella richiesta");
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        if (currentUsername == null || currentUsername.equals("")) {
            session.setAttribute("msg-error", "Errore nella richiesta");
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        UtenteDAO utenteDAO = new UtenteDAO(source);
        HashMap<String, String> map = new HashMap<>();

        if (username != null && !username.equals("")) {
            if (!controllaValiditaCampiUtente("username", username, currentUsername, utenteDAO)) {
                session.setAttribute("msg-error", "Lo username è già in uso");
                response.sendRedirect(request.getContextPath() + "/admin?part=utenti&action=edit&id=" + currentUsername);
                return;
            } else {
                map.put("username", username);
            }
        }

        if (email != null && !email.equals("")) {
            if (!controllaValiditaCampiUtente("email", email, currentUsername, utenteDAO)) {
                session.setAttribute("msg-error", "L'email è già in uso");
                response.sendRedirect(request.getContextPath() + "/admin?part=utenti&action=edit&id=" + currentUsername);
                return;
            } else {
                map.put("email", email);
            }
        }

        if (password != null && !password.equals("")) {

            if (!password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
                session.setAttribute("msg-error", "La password non rispetta i requisti");
                response.sendRedirect(request.getContextPath() + "/admin?part=utenti&action=edit&id=" + currentUsername);
                return;
            }

            map.put("password", password);
        }

        try {
            utenteDAO.doUpdate(map, "username = '" + currentUsername + "'");
            session.setAttribute("msg-success", "Profilo aggiornato con successo!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/admin?part=utenti");
    }

    private boolean controllaValiditaCampiUtente(String campo, String parametro, String email, UtenteDAO utenteDAO) {
        // Controllo che lo username non sia già in uso da un altro utente
        List<Utente> utenti = null;
        try {
            utenti = utenteDAO.doRetrieveByCondition(campo + " = '" + parametro + "' AND email <> '" + email + "'");

            return utenti == null || utenti.size() == 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
