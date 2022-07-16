<%@ page import="java.util.List" %>
<%@ page import="Model.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 16/07/2022
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Utente> utenti = (List<Utente>) request.getAttribute("utenti"); %>

<table id="basic-table" class="display">
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
                <td><a href="<%= request.getContextPath() %>/admin?part=utenti&action=edit&id=<%= utente.getUsername() %>"><i class="fas fa-edit"></i></a></td>
            </tr>
        <% } %>
    </tbody>
</table>