<%@ page import="java.util.List" %>
<%@ page import="Model.Utente.Utente" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Utente> utenti = (List<Utente>) request.getAttribute("utenti"); %>
<% Logger.consoleLog(Logger.JSP, "UTENTI.JSP"); %>

<div class="dashboard-container">
    <table id="basic-table" class="display dataTable cell-border text" style="color: white; text-align: center; word-wrap: break-word; word-break: break-all">
        <thead>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Azioni</th>
        </tr>
        </thead>
        <tbody>
        <% for (Utente utente : utenti) { %>
        <tr>
            <td><%= utente.getUsername() %></td>
            <td><%= utente.getEmail() %></td>
            <td><%= utente.getNome() %></td>
            <td><%= utente.getCognome() %></td>
            <%--method: GET  AdminServlet part: utenti action: edit--%>
            <td><a href="<%= request.getContextPath() %>/admin?part=utenti&action=edit&id=<%= utente.getUsername() %>"><i class="fas fa-edit"></i></a></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
