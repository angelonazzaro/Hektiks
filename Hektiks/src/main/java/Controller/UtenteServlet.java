package Controller;

import Model.Ordine.Ordine;
import Model.Ordine.OrdineDAO;
import Model.Recensione.Recensione;
import Model.Recensione.RecensioneDAO;
import Model.Utente.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UtenteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!controllaSeLoggato(request, response)) return;

        String part = request.getParameter("part");
        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");
        Utente user = (Utente) request.getSession().getAttribute("user");

        if (part == null) {

            try {
                List<Recensione> recensioni = new RecensioneDAO(source).doRetrieveByCondition("email_utente = '" + user.getEmail() + "'");
                List<Ordine> ordini = new OrdineDAO(source).doRetrieveByCondition("email_utente = '" + user.getEmail() + "'");

                double totaleSpeso = ordini.stream().map(Ordine::getPrezzo_totale).reduce(0.0D, Double::sum);

                request.setAttribute("recensioni", recensioni.size());
                request.setAttribute("ordini", ordini.size());
                request.setAttribute("totaleSpeso", totaleSpeso);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (part.equals("orders")) {

        } else if (part.equals("settings")) {

        }

        request.setAttribute("title", "Hektiks | " + user.getUsername());
        request.setAttribute("page", "utente/dashboard.jsp");

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!controllaSeLoggato(request, response)) return;
    }

    private boolean controllaSeLoggato(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        return true;
    }
}
