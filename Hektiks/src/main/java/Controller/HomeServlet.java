package Controller;

import Model.Carrello.Carrello;
import Model.Carrello.CarrelloDAO;
import Model.Gioco.GiocoDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import static Model.Storage.Entities.*;

import Utils.JSONResponse;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5982139499022378053L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("title", "Hektiks | Home Page");
        GiocoDAO giocoDAO = new GiocoDAO((DataSource) getServletContext().getAttribute("DataSource"));

        try {
            request.setAttribute("giochiDelMomento", giocoDAO.doRetrieveByCondition("TRUE LIMIT 9"));
            request.setAttribute("bestSellers", giocoDAO.doRetrieveByCondition("TRUE ORDER BY " + GIOCHI + ".numero_vendite DESC LIMIT 9"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) this.doGet(request, response);
        String action = request.getParameter("action");

        if (action == null || (!action.equals("register") && !action.equals("login"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("/ErrorHandlerServlet");
        }

        String email = request.getParameter("email"), password = request.getParameter("password");
        UtenteDAO utenteDAO = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        List<Utente> utenti;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (action.equals("login")) {

            try {
                utenti = utenteDAO.doRetrieveByCondition("email='" + email + "' AND password_utente=SHA1('" + password + "')");

                if (utenti.isEmpty()) {
                    out.write(gson.toJson(new JSONResponse<String>("error", "Email o password errati")));
                } else {
                    session = request.getSession();
                    session.setAttribute("user", utenti.get(0));

                    out.write(gson.toJson(new JSONResponse<String>("success")));
                }

                // [Granozio] vedo se l'utente loggato ha già un carrello
                CarrelloDAO carrelloDAO = new CarrelloDAO((DataSource) getServletContext().getAttribute("DataSource"));
                List<Carrello> carrelli =  carrelloDAO.doRetrieveByJoin("inner",
                        String.format("%s ON %s.email = %s.email_utente", UTENTI, UTENTI, CARRELLI), UTENTI + ".email = '" + utenti.get(0).getEmail() + "'",
                        UTENTI);

                // [Granozio] se è vuoto creo il carrello
                if (carrelli.isEmpty()) {

                    Carrello carrello = new Carrello();
                    carrello.setEmail_utente(utenti.get(0).getEmail());
                    carrello.setData_creazione(new Date(System.currentTimeMillis()));
                    carrello.setData_modifica(new Date(System.currentTimeMillis()));
                    carrelloDAO = new CarrelloDAO((DataSource) getServletContext().getAttribute("DataSource"));
                    carrelloDAO.doSave(carrello);
                }

                out.flush();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
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

                System.out.println(utenti);
                if (!utenti.isEmpty()) {
                    String lastUsername = utenti.get(utenti.size() - 1).getUsername();

                    // In questa maniera so quante cifre ci sono nell'ultimo username disponibile e faccio il substring per
                    // prendermi il numero
                    int digits = 0;
                    for (int i = 0; i < lastUsername.length(); i++)
                        if (Character.isDigit(lastUsername.charAt(i))) digits++;

                    if (digits > 0) {
                        offset = Integer.parseInt(lastUsername.substring(lastUsername.length() - digits)) + 1;
                    }

                    username = nome.toLowerCase() + "" + cognome.toLowerCase() + "" + offset;
                }

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
}
