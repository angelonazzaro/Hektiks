<%@ page import="java.util.List" %>
<%@ page import="Model.GiftCard.GiftCard" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<GiftCard> giftCards = (List<GiftCard>) request.getAttribute("giftCards"); %>
<% Logger.consoleLog(Logger.JSP, "GIFTCARDS.JSP"); %>

<div class="dashboard-container">
    <div style="margin-bottom: 5rem; text-align: right">
        <%--method: GET  AdminServlet part: giftcards, action: add--%>
        <a href="<%= request.getContextPath() %>/admin?part=giftcards&action=add" style="margin-bottom: 2rem;"
           class="btn">Aggiungi GiftCard</a>
    </div>
    <table id="basic-table" class="display dataTable cell-border text" style="color: white; text-align: center; word-wrap: break-word; word-break: break-all">
        <thead>
        <tr>
            <th>Codice</th>
            <th>Importo</th>
            <th>Data Creazione</th>
            <th>Data Utilizzo</th>
            <th>Utilizzata da</th>
        </tr>
        </thead>
        <tbody>
        <% for (GiftCard giftCard : giftCards) { %>
        <tr>
            <td><%= giftCard.getCodice_giftCard() %>
            </td>
            <td><%= String.format("%.2f€", giftCard.getImporto()) %>
            </td>
            <td><%= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss ").format(giftCard.getData_ora_creazione()) %>
            </td>
            <td>
                <%= giftCard.getData_ora_utilizzo() != null ? new SimpleDateFormat("dd/MM/yyyy hh:mm:ss ").format(giftCard.getData_ora_utilizzo()) : "-" %>
            </td>
            <td>
                <%= giftCard.getEmail_utente() != null ? giftCard.getEmail_utente() : "-" %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
