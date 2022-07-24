<%@ page import="Model.Utente.Utente" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utente utente = (Utente) request.getAttribute("utente"); %>
<% String profile_pic = utente.getProfile_pic() != null ? request.getContextPath() + "/assets/uploads/users" + utente.getProfile_pic() : request.getContextPath() + "/assets/uploads/users/avatar_placeholder.png"; %>
<% Logger.consoleLog(Logger.JSP, "UTENTE_EDIT.JSP"); %>

<div class="user-general-info">
    <div class="user-card">
        <div class="user-card-header">
            <img src="<%= profile_pic %>?time=<%=System.currentTimeMillis()%>"
                 alt="<%= utente.getUsername() %> immagine profilo">
        </div>
        <div class="user-card-body">
            <p class="hs-4" style="font-weight: bold; color: white; margin-bottom: 0.5rem"><%= utente.getUsername() %>
            </p>
            <p class="text" style="color: var(--grey)">Membro
                dal: <%= new SimpleDateFormat("dd MMMMMMMMMM yyyy ").format(utente.getData_registrazione()) %>
            </p>
        </div>
    </div>
</div>
<div class="settings-wrapper dashboard-container">
    <h1 class="hs-3">Form di modifica: <%= utente.getUsername() %>
    </h1>
    <%--method: POST  AdminServlet --%>
    <form action="<%= request.getContextPath() %>/admin" method="POST" id="form-settings" class="settings-container">
        <div class="form-body">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="componente" value="utente">
            <input type="hidden" name="current-username" value="<%= utente.getUsername() %>"/>
            <input type="hidden" name="current-email" value="<%= utente.getEmail() %>"/>
            <input type="hidden" name="current-password" value="<%= utente.getPassword_utente() %>"/>

            <div class="row">
                <input type="text" class="form-control" name="username" value="<%= utente.getUsername() %>"
                       placeholder="Username">
                <input type="email" class="form-control" name="email" value="<%= utente.getEmail() %>"
                       placeholder="Email">
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