package Controller;

import Model.Carrello.CarrelloDAO;
import Model.Gioco.Gioco;
import Model.Gioco.GiocoDAO;
import Model.Ordine.Ordine;
import Model.Ordine.OrdineDAO;
import Model.Pagamento.Pagamento;
import Model.Pagamento.PagamentoDAO;
import Model.Prodotto.ProdottoDAO;
import Model.Prodotto_Ordine.Prodotto_Ordine;
import Model.Prodotto_Ordine.Prodotto_OrdineDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import Utils.Logger.Logger;
import Utils.LoginChecker;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class AcquistoServlet extends HttpServlet {

    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.consoleLog(Logger.SERVLET, "ACQUISTO SERVLET DO POST");

        //controllo se l'utente è loggato, altrimenti non può fare l'acquisto

        if (!LoginChecker.controllaSeLoggato(request, response, "Per poter effettuare un acquisto devi accedere al tuo account", false))
            return;

        String from = request.getParameter("from");

        //controllo se la richiesta proviene dalla pagina del carrello o del gioco

        if (!from.equals("carrello") && !from.equals("gioco")) {

            request.getSession().setAttribute("msg-error", "Qualcosa è andato storto!");
            response.sendRedirect(request.getRequestURI());

            return;
        }

        List<Gioco> giochiDaAcquistare = new ArrayList<>();
        DataSource source = (DataSource) getServletContext().getAttribute("DataSource");
        GiocoDAO giocoDAO = new GiocoDAO(source);
        double prezzoTotale = 0, prezzoGioco;
        Gioco gioco = null;
        HttpSession session = request.getSession();
        HashMap<String, Integer> carrello = (HashMap<String, Integer>) session.getAttribute("carrello");

        // from -> "carrello", la richiesta proviene da carrello.jsp quindi l'utente vuole acquistare tutto quello che sta nel carrello
        // from -> "gioco", la richiesta proviene da gioco.jsp quindi l'utente vuole acquistare solo quello specifico gioco

        if (from.equals("carrello")) {

            for (String key : carrello.keySet()) {

                //per ogni item nel carrello calcolo il prezzo e lo aggiungo al prezzo totale
                //e lo aggiungo alla lista dei giochi da acquistare

                try {

                    gioco = giocoDAO.doRetrieveByKey(key);

                    if (gioco.getPercentuale_sconto() > 0)
                        prezzoGioco = gioco.getPrezzo() - ((gioco.getPrezzo() * gioco.getPercentuale_sconto()) / 100);

                    else
                        prezzoGioco = gioco.getPrezzo();

                    prezzoTotale += prezzoGioco * carrello.get(key);
                    giochiDaAcquistare.add(gioco);

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        } else {

            // la richiesta proviene da gioco.jsp quindi l'utente vuole acquistare solo quello specifico gioco

            String codice_gioco = request.getParameter("codice_gioco");

            try {

                gioco = giocoDAO.doRetrieveByKey(codice_gioco);

                if (gioco.getPercentuale_sconto() > 0)
                    prezzoTotale = gioco.getPrezzo() - ((gioco.getPrezzo() * gioco.getPercentuale_sconto()) / 100);

                else
                    prezzoTotale = gioco.getPrezzo();

                giochiDaAcquistare.add(gioco);

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        Utente utente = (Utente) session.getAttribute("user");

        //controllo che il saldo dell'utente sia sufficiente, altrimenti non può acquistare

        if (utente.getSaldo() < prezzoTotale) {

            session.setAttribute("msg-error", "Il tuo saldo non è sufficiente ad effettuare l'acquisto");
            response.sendRedirect(request.getContextPath() + "/");

            return;
        }

        OrdineDAO ordineDAO = new OrdineDAO(source);
        Prodotto_OrdineDAO prodotto_ordineDAO = new Prodotto_OrdineDAO(source);

        Ordine ordine = new Ordine();
        String codice_ordine = null;

        // Mi assicuro che il codice ordine sia unico
        try {

            do {

                codice_ordine = generaCodiceOrdine();

            } while (ordineDAO.doRetrieveByKey(utente.getEmail(), codice_ordine) != null);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        ordine.setCodice_ordine(codice_ordine);
        Timestamp date = new Timestamp(System.currentTimeMillis());
        ordine.setEmail_utente(utente.getEmail());
        ordine.setData_ora_ordinazione(date);
        ordine.setPrezzo_totale(prezzoTotale);

        try {

            // Se l'ordine è andato a buon fine, inserisco i prodotti in db
            // Il procedimento è uguale per entrambi i valori di "from"

            if (ordineDAO.doSave(ordine)) {

                Prodotto_Ordine prodotto_ordine = new Prodotto_Ordine();
                prodotto_ordine.setEmail_utente(utente.getEmail());
                prodotto_ordine.setData_ora_creazione(date);
                prodotto_ordine.setCodice_ordine(codice_ordine);

                PagamentoDAO pagamentoDAO = new PagamentoDAO(source);
                Pagamento pagamento = new Pagamento();
                pagamento.setCodice_ordine(codice_ordine);
                pagamento.setEmail_utente(utente.getEmail());
                pagamento.setImporto(prezzoTotale);
                pagamento.setData_ora_pagamento(date);

                pagamentoDAO.doSave(pagamento);

                for (Gioco giocoDaAcquistare : giochiDaAcquistare) {

                    prodotto_ordine.setCodice_gioco(giocoDaAcquistare.getCodice_gioco());

                    //aggiorno quantità

                    if (from.equals("carrello"))
                        prodotto_ordine.setQuantita(carrello.get(gioco.getCodice_gioco()));

                    else
                        prodotto_ordine.setQuantita(1);

                    //calcolo sconto

                    if (giocoDaAcquistare.getPercentuale_sconto() > 0)
                        prodotto_ordine.setPrezzo(giocoDaAcquistare.getPrezzo() - ((giocoDaAcquistare.getPrezzo() * giocoDaAcquistare.getPercentuale_sconto()) / 100));

                    else
                        prodotto_ordine.setPrezzo(giocoDaAcquistare.getPrezzo());

                    prodotto_ordineDAO.doSave(prodotto_ordine);
                    HashMap<String, Integer> map = new HashMap<>();

                    // Aggiorno le vendite del gioco
                    map.put("numero_vendite", giocoDaAcquistare.getNumero_vendite() + prodotto_ordine.getQuantita());
                    map.put("quantita_disponibile", giocoDaAcquistare.getQuantita_disponibile() - prodotto_ordine.getQuantita());
                    giocoDAO.doUpdate(map, "codice_gioco = '" + giocoDaAcquistare.getCodice_gioco() + "'");
                }


                // Aggiorno il saldo dell'utente sia in sessione che nel db
                utente.setSaldo(utente.getSaldo() - prezzoTotale);
                session.setAttribute("user", utente);
                UtenteDAO utenteDAO = new UtenteDAO(source);
                HashMap<String, Double> map = new HashMap<>();
                map.put("saldo", utente.getSaldo());
                utenteDAO.doUpdate(map, "email = '" + utente.getEmail() + "'");

                // Svuoto il carrello in sessione e quello nel db <=> from == "carrello"
                if (from.equals("carrello")) {

                    session.setAttribute("carrello", new HashMap<String, Integer>());
                    session.setAttribute("quantita_carrello", 0);

                    CarrelloDAO carrelloDAO = new CarrelloDAO(source);
                    ProdottoDAO prodottoDAO = new ProdottoDAO(source);

                    carrelloDAO.doDelete("email_utente = '" + utente.getEmail() + "'");
                    prodottoDAO.doDelete("email_utente = '" + utente.getEmail() + "'");
                }

                session.setAttribute("msg-success", "Ordine <b>#" + ordine.getCodice_ordine() + "</b> effettutato con successo!");
                response.sendRedirect(request.getContextPath() + "/");

            } else {

                session.setAttribute("msg-error", "Qualcosa è andato storto");
                response.sendRedirect(request.getRequestURI());
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * Genera un codice ordine univoco
     **/

    private String generaCodiceOrdine() {

        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;

        for (int i = 0; i < length; i++)
            sb.append(alfabeto.charAt(random.nextInt(alfabeto.length())));

        return sb.toString();
    }
}
