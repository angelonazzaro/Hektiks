package Controller;

import Model.GiftCard.GiftCardDAO;
import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
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

        try {
            request.setAttribute("giochi", new GiocoDAO((DataSource) getServletContext().getAttribute("DataSource")).doRetrieveAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) this.doGet(request, response);

        String action = request.getParameter("action");

        if (!action.equals("register") && !action.equals("login")) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect("/ErrorHandlerServlet");
        }

        String email = request.getParameter("email"), password = request.getParameter("password");
        UtenteDAO utenteDAO = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
        PrintWriter out = response.getWriter();
        Gson gsonObj = new Gson();

        List<Utente> utenti = null;

        if (action.equals("login")) {

            try {
                utenti = utenteDAO.doRetrieveByCondition("email='" + email + "' AND password_utente=SHA1('" + password + "')");

                if (utenti.isEmpty()) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    out.write(gsonObj.toJson(new JSONResponse<String>("danger", "Credenziali errate")));
                } else {
                    session = request.getSession();
                    session.setAttribute("user", utenti.get(0));

                    out.write(gsonObj.toJson(new JSONResponse<String>("success")));
                }

                out.flush();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                utenti = utenteDAO.doRetrieveByCondition("email='" + email + "'");

                if (!utenti.isEmpty()) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    out.write(gsonObj.toJson(new JSONResponse<String>("danger", "Email già registrata")));
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

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                if (utenteDAO.doSave(utente))
                    out.write(gsonObj.toJson(new JSONResponse<String>("success", "Registrazione avvenuta con successo!")));
                else
                    out.write(gsonObj.toJson(new JSONResponse<String>("danger", "Qualcosa è andato storto :(")));

                out.flush();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}