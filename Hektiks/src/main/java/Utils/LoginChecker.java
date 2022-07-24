package Utils;

import Model.Utente.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginChecker {

    /**
     * Classe che fornisce un metodo per verificare se l'utente è loggato o no.
     **/

    public static boolean controllaSeLoggato(HttpServletRequest request, HttpServletResponse response, String msg, boolean isAdmin) throws IOException {

    HttpSession session = request.getSession(false);

    // se l'utente non è loggato e prova ad accedere ad una risorsa che richiede un login, viene reindirizzato alla home page

    if (session == null || session.getAttribute("user") == null) {

        if (session == null)
            session = request.getSession(true);

        if (msg.length() > 0)
            session.setAttribute("msg-error", msg);

        response.sendRedirect(request.getContextPath() + "/");

        return false;
    }

    // se l'utente non è un admin e prova ad accedere ad una risorsa senza autorizzazione viene reindirizzato alla home page
    // e viene mostrato il messaggio d'errore

    if (isAdmin) {

        if (!((Utente) session.getAttribute("user")).isRuolo()) {

            session.setAttribute("msg-error", "Non sei autorizzato a visualizzare questa pagina");
            response.sendRedirect(request.getContextPath() + "/");

            return false;
        }
    }

    return true;
    }
}
