<%@ page import="Model.Ordine.Ordine" %>
<%@ page import="Model.Prodotto_Ordine.Prodotto_OrdineDAO" %>
<%@ page import="Model.Gioco.GiocoDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Model.Prodotto_Ordine.Prodotto_Ordine" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini"); %>
<% Prodotto_OrdineDAO prodotto_ordineDAO = (Prodotto_OrdineDAO) request.getAttribute("prodottoOrdineDAO"); %>
<% GiocoDAO giocoDAO = (GiocoDAO) request.getAttribute("giocoDAO"); %>
<% Logger.consoleLog(Logger.JSP, "ORDERS.JSP"); %>

<div class="orders-wrapper dashboard-container">
    <h1 class="hs-3">I miei ordini</h1>

    <% if (ordini.size() > 0) { %>
    <div class="orders-container">
        <% for (Ordine ordine : ordini) { %>
        <div class="order">
            <% try {
                Gioco gioco;
                PrintWriter writer = response.getWriter();
                List<Prodotto_Ordine> prodottoOrdini = prodotto_ordineDAO.doRetrieveByCondition("codice_ordine = '" + ordine.getCodice_ordine() + "'");
                for (Prodotto_Ordine prodottoOrdine : prodottoOrdini) {
                    gioco = giocoDAO.doRetrieveByKey(prodottoOrdine.getCodice_gioco());
                    //method: GET  GiocoServlet
                    out.write(
                            "<div class='game-order'>" +
                                    "<div class='game-info'>" +
                                    "<a href='" + request.getContextPath() + "/gioco?codice_gioco=" + gioco.getCodice_gioco() + "'><img src='" + gioco.getCopertina() + "' alt='" + gioco.getTitolo() + "- Copertina' /></a>" +
                                    "<div>" +
                                    "<p class='text'>" + gioco.getTitolo() + "</p>" +
                                    "<p class='text'>Quantità:" + prodottoOrdine.getQuantita() + "</p>" +
                                    "</div>" +
                                    "</div>" +
                                    "<div class='game-price'>" +
                                    "<p class='text'>" + String.format("%.2f€", prodottoOrdine.getQuantita() * prodottoOrdine.getPrezzo()) + "</p>" +
                                    "</div>" +
                                    "</div>" +
                                    "<hr class='game-order-separator' />");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } %>
            <div class="order-total">
                <p class="hs-4">Totale</p>
                <p class="hs-4"><%= String.format("%.2f€", ordine.getPrezzo_totale())%></p>
            </div>
            <ul class="order-info text">
                <li style="margin-right: 2rem">Ordine #<%= ordine.getCodice_ordine() %></li>
                <li><%= new SimpleDateFormat("dd MMMMMMMMMM yyyy HH:mm:ss").format(ordine.getData_ora_ordinazione()) %></li>
            </ul>
        </div>
        <% } %>
    </div>
    <% } else { %>
    <div style="width: 100%; margin-top: 2rem; text-align: center;">
        <h1 class="hs-3" style="color: white;">Non hai effettutato nessun ordine.</h1>
    </div>
    <% } %>
</div>
