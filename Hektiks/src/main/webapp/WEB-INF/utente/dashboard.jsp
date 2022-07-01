<%@ page import="Model.Utente.Utente" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 01/07/2022
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String partPath = (String) request.getAttribute("part"); %>
<% Utente user = (Utente) session.getAttribute("user"); %>

<% String profile_pic = user.getProfile_pic() != null ? request.getContextPath() + "/assets/uploads/users" + user.getProfile_pic() : request.getContextPath() + "/assets/uploads/users/avatar_placeholder.png"; %>

<div class="user-general-info">
    <div class="user-card">
        <div class="user-card-header">
            <img src="<%= profile_pic %>" alt="<%= user.getUsername() %> immagine profilo">
        </div>
        <div class="user-card-body">
            <p class="hs-4" style="font-weight: bold; color: white; margin-bottom: 0.5rem"><%= user.getUsername() %></p>
            <p class="text" style="color: var(--grey)">Membro dal: <%= new SimpleDateFormat("dd MMMMMMMMMM yyyy ").format(user.getData_registrazione()) %></p>
        </div>
    </div>
</div>
<div class="breadcrumb text">
    <ul class="user-links">
        <li class="user-link active"><a href="#">
            Dashboard</a>
        </li>
        <% if (user.isRuolo()) {%>
            <li class="user-link"><a href="#">Admin</a></li>
        <% } %>
        <li class="user-link"><a href="#">I miei ordini</a></li>
        <li class="user-link"><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
    </ul>
    <div class="user-settings-preview">
        <a href="#" class="user-link"><i class="fas fa-cog"></i>
            Impostazioni</a>
    </div>
</div>

<% if (partPath == null) { %>

    <% int ordini = (Integer) request.getAttribute("ordini"); %>
    <% int recensioni = (Integer) request.getAttribute("recensioni"); %>
    <% double totaleSpeso = (Double) request.getAttribute("totaleSpeso"); %>

    <div class="user-overview">
        <div class="dashboard-content">
            <div class="overview-card dashboard-card">
                <div class="dashboard-card-header">
                    <img src="<%= request.getContextPath() %>/assets/images/icons/icon-dashboard.svg" alt="">
                    <h1 class="hs-3">
                        Overview
                    </h1>
                </div>
                <div class="dashboard-card-body">
                    <hr>
                    <div class="overview-card-content">
                        <div class="overview-info hs-4">
                            <p><%= ordini %></p>
                            <p>Ordini</p>
                        </div>
                        <div class="overview-info hs-4">
                            <p><%= recensioni %></p>
                            <p>Recensioni</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="dashboard-card">
                <div class="dashboard-card-header">
                    <img src="<%= request.getContextPath() %>/assets/images/icons/icon-wallet.svg" alt="">
                    <h1 class="hs-3">
                        Portafoglio
                    </h1>
                </div>
                <div class="dashboard-card-body">
                    <hr>
                    <p class="text">Saldo <span class="hs-5" style="color: var(--grey)"><%= String.format("%.2f€", user.getSaldo())%></span></p>
                </div>
            </div>
            <div class="dashboard-card">
                <div class="dashboard-card-header">
                    <img src="<%= request.getContextPath() %>/assets/images/icons/icon-bank.svg" alt="">
                    <h1 class="hs-3">
                        Totale speso
                    </h1>
                </div>
                <div class="dashboard-card-body">
                    <hr>
                    <p class="hs-5" style="color: var(--grey)"><%= String.format("%.2f€", totaleSpeso)%></p>
                </div>
            </div>
            <div class="dashboard-card giftcards">
                <div class="dashboard-card-header">
                    <img src="<%= request.getContextPath() %>/assets/images/icons/icon-gift.svg" alt="">
                    <h1 class="hs-3">
                        Giftcards
                    </h1>
                </div>
                <div class="dashboard-card-body">
                    <hr>
                    <form action="#" method="POST">
                        <div class="row">
                            <input type="text" placeholder="Codice Gift Card" class="form-control">
                            <button class="btn" type="submit">Riscatta</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<% } %>
