<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.util.List" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Gioco> giochi = (List<Gioco>) request.getAttribute("giochi"); %>
<% Logger.consoleLog(Logger.JSP, "PRODOTTI.JSP"); %>

<div class="dashboard-container">
    <div style="margin-bottom: 5rem; text-align: right">
        <%--method: GET  AdminServlet part: prodotti, action: add--%>
        <a href="<%= request.getContextPath() %>/admin?part=prodotti&action=add" style="margin-bottom: 2rem;" class="btn">Aggiungi gioco</a>
    </div>
    <table id="basic-table" class="display dataTable cell-border text" style="color: white; text-align: center; word-wrap: break-word; word-break: break-all">
        <thead>
        <tr>
            <th>Codice</th>
            <th>Titolo</th>
            <th>Quantità</th>
            <th>Prezzo</th>
            <th>Azioni</th>
        </tr>
        </thead>
        <tbody>
        <% for (Gioco gioco : giochi) { %>
        <tr>
            <td><%= gioco.getCodice_gioco() %></td>
            <td><%= gioco.getTitolo() %></td>
            <td><%= gioco.getQuantita_disponibile() %></td>
            <td><%= String.format("%.2f€", gioco.getPrezzo()) %></td>
            <td>
                <%--method: GET  AdminServlet part: prodotti, action: edit--%>
                <a href="<%= request.getContextPath() %>/admin?part=prodotti&action=edit&id=<%= gioco.getCodice_gioco() %>"><i class="fas fa-edit"></i></a>
                <%--method: GET  AdminServlet part: prodotti, delete: add--%>
                <a href="<%= request.getContextPath() %>/admin?part=prodotti&action=delete&id=<%= gioco.getCodice_gioco() %>"><i class="fas fa-trash-alt"></i></a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
