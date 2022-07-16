package Utils;

import Model.Utente.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public interface LoginChecker {

     default boolean controllaSeLoggato(HttpServletRequest request, HttpServletResponse response, String msg, boolean isAdmin) throws IOException {
         HttpSession session = request.getSession(false);

         if (session == null || session.getAttribute("user") == null) {
             if (session == null)
                 session = request.getSession(true);

             if (msg.length() > 0)
                session.setAttribute("msg-error", msg);

             response.sendRedirect(request.getContextPath() + "/");
             return false;
         }

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
