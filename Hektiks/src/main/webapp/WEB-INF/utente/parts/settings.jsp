<%@ page import="Model.Utente.Utente" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Utente utente = (Utente) session.getAttribute("user"); %>
<% Logger.consoleLog(Logger.JSP, "SETTINGS.JSP"); %>

<div class="settings-wrapper dashboard-container">
    <h1 class="hs-3">Le mie impostazioni</h1>
    <%--method: POST  UtenteServlet --%>
    <form action="<%= request.getContextPath() %>/utente" method="POST" enctype="multipart/form-data" id="form-settings" class="settings-container">
        <div class="form-body">
            <div class="row">
                <input type="file" class="form-control" name="profile_pic" id="profile_pic" accept=".jpg, .jpeg, .png">
                <input type="text" class="form-control"  name="username" id="username" placeholder="<%= utente.getUsername() %>" value="">
            </div>
            <div class="row">
                <input type="email" class="form-control" name="email" placeholder="<%= utente.getEmail() %>">
            </div>
            <div class="row">
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
        </div>
        <div class="form-footer">
            <button type="submit" class="btn" style="width: 100%">Invia</button>
        </div>
    </form>
</div>