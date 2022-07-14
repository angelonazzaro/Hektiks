package Controller;

import Model.Gioco.GiocoDAO;
import Model.Ordine.Ordine;
import Model.Ordine.OrdineDAO;
import Model.Prodotto_Ordine.Prodotto_OrdineDAO;
import Model.Recensione.Recensione;
import Model.Recensione.RecensioneDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import Utils.Logger.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024,      // 1 MB
        maxRequestSize = 1024 * 1024 * 5   // 5 MB
)

public class UtenteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logger.consoleLog(Logger.INFO, "UTENTE SERVLET DO GET");

        if (!controllaSeLoggato(request, response)) return;

        String part = request.getParameter("part");
        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");
        Utente user = (Utente) request.getSession().getAttribute("user");

        // se 'part' è nullo, mostro la dashboard
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
            try {
                request.setAttribute("ordini", new OrdineDAO(source).doRetrieveByCondition("email_utente = '" + user.getEmail() + "'"));
                request.setAttribute("prodottoOrdineDAO", new Prodotto_OrdineDAO(source));
                request.setAttribute("giocoDAO", new GiocoDAO(source));
                request.setAttribute("part", "parts/orders.jsp");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (part.equals("settings")) {
            request.setAttribute("part", "parts/settings.jsp");
        }

        request.setAttribute("title", "Hektiks | " + user.getUsername());
        request.setAttribute("page", "utente/dashboard.jsp");
        request.setAttribute("scripts", new String[]{"user.js"});

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Logger.consoleLog(Logger.INFO, "UTENTE SERVLET DO POST");

        if (!controllaSeLoggato(request, response)) return;

        Part filePart = request.getPart("profile_pic");
        HttpSession session = request.getSession(false);
        Utente utente = (Utente) session.getAttribute("user");

        // L'utente ha effettivamente caricato un'immagine

        if (filePart != null) {
            String fileName = filePart.getSubmittedFileName();

            // Controllo che il file sia un'immagine
            if (!fileName.endsWith(".jpeg") && !fileName.endsWith(".jpg") && !fileName.endsWith(".png")) {
                session.setAttribute("msg-error", "Il file caricato non è un'immagine");
                response.sendRedirect(request.getContextPath() + "/utente?part=settings");
                return;
            }

            if (filePart.getSize() > 1048576) {
                session.setAttribute("msg-error", "La dimensione dell'immagine è troppo grande");
                response.sendRedirect(request.getContextPath() + "/utente?part=settings");
                return;
            }

            String appPath = request.getServletContext().getRealPath("/");
            String newPicPath;

            File userProfilePicFolder = new File(appPath + "/assets/uploads/users/" + utente.getUsername());

            //se non ha un folder per l'utente, lo creo
            if (!userProfilePicFolder.exists()) {

                if (!userProfilePicFolder.mkdir()) {

                    session.setAttribute("msg-error", "Errore durante il caricamento dell'immagine");
                    response.sendRedirect(request.getContextPath() + "/utente?part=settings");

                    return;
                }
                //se l'utente non avava una propic la salvo per la prima volta
                else {

                    String ext = fileName.split("\\.")[1];
                    newPicPath = userProfilePicFolder.getPath() + "\\profile_pic." + ext;
                    filePart.write(newPicPath);
                }

            }
            // se l'utente aveva già un'immagine
            else {

                //salvo file di backup
                String oldFile = trovaRinominaFile(userProfilePicFolder.getPath(), new String[]{"jpeg", "jpg", "png"});

                String ext = fileName.split("\\.")[1];
                newPicPath = userProfilePicFolder.getPath() + "\\profile_pic." + ext;
                filePart.write(newPicPath);
                System.out.println("SALVO IL FILE: " + newPicPath);

                //cancello il file di backup
                if(new File(newPicPath).exists())
                    if(oldFile != null)
                        new File(oldFile).delete();

                //Todo: rollback in caso di immagine incompatibile
                else {

                    session.setAttribute("msg-error", "Errore durante il caricamento dell'immagine");
                    response.sendRedirect(request.getContextPath() + "/utente?part=settings");
                    return;
                }


            }

            // serve aggiornare ogni volta anche se il path rimane uguale?? no angioletto ti apro il culo
            response.sendRedirect(request.getContextPath() + "/utente?part=settings");
            utente.setProfile_pic("/" + utente.getUsername() + "/" + "profile_pic." + newPicPath.substring(newPicPath.lastIndexOf(".") + 1));

            try {

                HashMap<String, String> map = new HashMap<>();
                map.put("profile_pic", utente.getProfile_pic());
                new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource")).doUpdate(map, "email = '" + utente.getEmail() + "'");
                session.setAttribute("user", utente);
                session.setAttribute("msg-success", "Immagine caricata con successo");

            } catch (SQLException e) {

                e.printStackTrace();
            }
//
//            File renamedPic = new File(renamedPath);
//
//            // Ridimensiono l'immagine a 360x360
//            BufferedImage bufferedImage = ImageIO.read(newPic);
//            ImageIO.write(creaCopiaRidimensionata(bufferedImage), ext, renamedPic);
//
//            if (renamedPic.exists()) {
//                if (oldPic != null) {
//                    if (!oldPic.delete()) {
//                        renamedPic.delete();
//                        session.setAttribute("msg-error", "Errore durante il caricamento dell'immagine");
//                        response.sendRedirect(request.getContextPath() + "/utente?part=settings");
//                        return;
//                    }
//                }
//
//                utente.setProfile_pic("/" + utente.getUsername() + "/" + "profile_pic." + ext);
//                try {
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("profile_pic", utente.getProfile_pic());
//                    new UtenteDAO((DataSource) getServletContext().getAttribute("DataSource")).doUpdate(map, "email = '" + utente.getEmail() + "'");
//                    session.setAttribute("user", utente);
//                    session.setAttribute("msg-success", "Immagine caricata con successo");
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                newPic.delete();
//                session.setAttribute("msg-error", "Errore durante il caricamento dell'immagine");
//            }
        }
    }

    private String trovaRinominaFile(String path, String[] ext) {

        File[] files = new File[ext.length];

        for (String s : ext) {

            File f = new File(path + "\\profile_pic." + s);

            if (f.exists()) {

                f.renameTo(new File(path + "\\profile_pic_old." + s));
                return path + "\\profile_pic_old." + s;
            }
        }
        return null;
    }

    private BufferedImage creaCopiaRidimensionata(BufferedImage image) {
        BufferedImage scaledBI = new BufferedImage(360, 360, image.getType());
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(image, 0, 0, 360, 360, null);
        g.dispose();
        return scaledBI;
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
