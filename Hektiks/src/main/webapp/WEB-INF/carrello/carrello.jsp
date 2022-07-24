<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Utils.Logger.Logger" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Gioco> giochiCarrello = request.getAttribute("giochiCarrello") != null ? (List<Gioco>) request.getAttribute("giochiCarrello") : null; %>
<% Logger.consoleLog(Logger.JSP, "CARRELLO.JSP"); %>

<% if (giochiCarrello == null || giochiCarrello.isEmpty()) { %>
    <div style="width: 100%; text-align: center; color: white;">
        <h1 class="hs-3">Il tuo carrello è vuoto.</h1>
    </div>
<% } else { %>
    <!-- mi serve per capire la quantità nel carrello di ogni gioco -->
    <% HashMap<String, Integer> carrello = (HashMap<String, Integer>) session.getAttribute("carrello"); %>
    <% double totalPrice = 0, prezzo; %>
    <div class="cart-heading">
        <h1 class="hs-3">Carrello</h1>
        <p class="text">Prezzo</p>
    </div>
    <hr style="margin-bottom: 2rem" />
    <div class="cart-container">
        <% for (Gioco gioco : giochiCarrello) { %>
            <% int quantitaCarrello = carrello.get(gioco.getCodice_gioco()); %>
            <% if (gioco.getPercentuale_sconto() > 0) { %>
                <% prezzo = gioco.getPrezzo() - ((gioco.getPrezzo() * gioco.getPercentuale_sconto()) / 100); %>
            <% } else { %>
                <% prezzo = gioco.getPrezzo(); %>
            <% } %>
            <% prezzo = Math.floor(prezzo * 100) / 100; %>
            <% totalPrice += prezzo * quantitaCarrello; %>
            <div class="cart-item <%= gioco.getCodice_gioco() %>">
                <div class="cart-item-preview">
                    <img class="cart-item-img" src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina">
                    <div class="cart-item-info">
                        <h2 class="hs-4"><%= gioco.getTitolo() %></h2>
                        <div class="cart-item-tools" data-code="<%= gioco.getCodice_gioco() %>" data-price="<%= prezzo %>" data-quantity="<%= quantitaCarrello %>">
                            <select name="quantita" class="text">
                                <% for (int i = 1; i <= gioco.getQuantita_disponibile(); i++) { %>
                                <option value="<%= i %>" <%= i == quantitaCarrello ? "selected" : "" %>>Q.tà: <%= i %></option>
                                <% } %>
                            </select>
                            <a style="cursor: pointer" class="remove-cart-btn text">Rimuovi</a>
                        </div>
                    </div>
                </div>
                <div class="cart-item-price hs-4">
                    <%= String.format("%.2f", prezzo).replace(",", ".") %> €
                </div>
            </div>
            <hr class="cart-item-separator <%= gioco.getCodice_gioco() %>">

        <% } %>
        <div id="cart-total-price" class="hs-4">
            <p>Totale: <span id="cart-total"><%= String.format("%.2f", totalPrice).replace(",", ".") %></span>€</p>
        </div>
        <%--method: POST  AcquistoServlet--%>
        <form style="width: 100%" action="<%= request.getContextPath() %>/acquisto" method="POST">
            <input type="hidden" name="from" value="carrello">
            <button type="submit" class="btn" style="width: 100%; text-align: center; margin-top: 2rem;">Procedi all'acquisto</button>
        </form>
    </div>

<% } %>