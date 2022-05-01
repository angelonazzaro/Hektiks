package Controller;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import Utils.JSONResponse;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import javax.sql.DataSource;

public class HomeServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5982139499022378053L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("title", "Hektiks | Home Page");

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null && (Utente) session.getAttribute("user") != null) this.doGet(request, response);

        String action = (String) request.getParameter("action");

        if (!action.equals("register") && !action.equals("login")) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect("/ErrorHandlerServlet");
        }

        String email = request.getParameter("email"), password = request.getParameter("password");
        UtenteDAO utenteDAO = new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
        PrintWriter out = response.getWriter();
        Gson gsonObj = new Gson();

        if (action.equals("login")) {
            try {
                List<Utente> utenti = utenteDAO.doRetrieveByCondition("email='" + email + "' AND password=SHA1('" + password + "')");

                if (utenti.isEmpty()) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    out.write(gsonObj.toJson(new JSONResponse<String>("danger", "Credenziali errate")));
                    out.flush();
                    return;
                }

                session = request.getSession();
                session.setAttribute("user", utenti.get(0));

                out.write(gsonObj.toJson(new JSONResponse<String>("success")));
                out.flush();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                List<Utente> utenti = utenteDAO.doRetrieveByCondition("email='" + email + "'");

                if (!utenti.isEmpty()) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                   out.write(gsonObj.toJson(new JSONResponse<String>("danger", "Email già registrata")));
                   out.flush();
                   return;
                }

                String nome = (String) request.getParameter("nome"), cognome = (String) request.getParameter("cognome");
                String username = nome.toLowerCase(Locale.ROOT) + "" + cognome.toLowerCase(Locale.ROOT);

                utenti = utenteDAO.doRetrieveByCondition("username LIKE '" + username + "%'");
                if (utenti.isEmpty()) {
                    Utente utente = new Utente();
                    utente.setEmail(email);
                    utente.setUsername(username);
                    utente.setNome(nome);
                    utente.setCognome(cognome);
                    utente.setPassword_utente(password);
                    utente.setData_registrazione(new Date(System.currentTimeMillis()));

                    if (utenteDAO.doSave(utente))
                        out.write(gsonObj.toJson(new JSONResponse<String>("success", "Registrazione avvenuta con successo!")));
                    else
                        out.write(gsonObj.toJson(new JSONResponse<String>("danger", "Qualcosa è andato storto :(")));

                    out.flush();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}