<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 15/06/2022
  Time: 09:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Gioco> giochiCarrello = request.getAttribute("giochiCarrello") != null ? (List<Gioco>) request.getAttribute("giochiCarrello") : null; %>

<% if (giochiCarrello == null || giochiCarrello.isEmpty()) { %>
    <h1 class="hs-3">Il tuo carrello è vuoto.</h1>
<% } else { %>
    <!-- mi serve per capire la quantità nel carrello di ogni gioco -->
    <% HashMap<String, Integer> carrello = (HashMap<String, Integer>) session.getAttribute("carrello"); %>
    <div class="cart-heading">
        <h1 class="hs-3">Carrello</h1>
        <p class="text">Prezzo</p>
    </div>
    <hr />
    <div class="cart-container">
        <% for (Gioco gioco : giochiCarrello) { %>
            <% int quantitaCarrello = carrello.get(gioco.getCodice_gioco()); %>
            <div class="cart-item">
                <div class="cart-item-preview">
                    <img class="cart-item-img" src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina">
                    <div class="cart-item-info">
                        <h2 class="hs-4"><%= gioco.getTitolo() %></h2>
                        <div class="cart-item-tools" data-code="<%= gioco.getCodice_gioco() %>">
                            <select name="quantita">
                                <% for (int i = 1; i <= gioco.getQuantita_disponibile(); i++) { %>
                                <option value="<%= i %>" <%= i == quantitaCarrello ? "selected" : "" %>>Q.tà: <%= i %></option>
                                <% } %>
                            </select>
                            <a style="cursor: pointer" class="remove-cart-btn">Rimuovi</a>
                        </div>
                    </div>
                </div>
                <div class="cart-item-price text">
                    <% if (gioco.getPercentuale_sconto() > 0) { %>
                        <%= String.format("%.2f€", gioco.getPrezzo() - ((gioco.getPrezzo() * gioco.getPercentuale_sconto()) / 100)) %>
                    <% } else { %>
                        <%= String.format("%.2f€", gioco.getPrezzo()) %>
                    <% } %>
                </div>
            </div>
        <% } %>
    </div>
<% } %>