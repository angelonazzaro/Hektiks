<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Model.Genere.Genere" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco_Genere.Gioco_Genere" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 12/06/2022
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Gioco gioco = (Gioco) request.getAttribute("gioco"); %>
<% List<Gioco_Genere> generi = (List<Gioco_Genere>) request.getAttribute("generi"); %>
<%--<% Sconto sconto = gioco.getJoin() != null ? (Sconto) gioco.getJoin().get(0) : null; %>--%>

<div class="game-presentation">
    <div class="banner">
        <img src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina">
    </div>
    <div class="game-card">
        <div class="title">
            <h1 class="hs-3"><%= gioco.getTitolo() %></h1>
        </div>
        <div class="subinfos">
            <p class="subinfo text <%= gioco.getQuantita_disponibile() > 0 ? "tick" : "cross" %>">
               <%= gioco.getQuantita_disponibile() > 0 ? "" : " Non" %> Disponibile
            </p>
        </div>
        <div class="amount">
            <p class="original-amount text">60€</p>
            <p class="discount-amount text ">-30%</p>
            <p class="current-amount hs-3">41.90€</p>
        </div>
        <div class="actions">
            <a class="btn"><span><i class="fas fa-heart"></i></span></a>
            <button class="btn">Compra</button>
        </div>
    </div>
    <div class="game-separator"></div>
    <div class="details">
        <div class="headline">
            <h1 class="hs-3">Visuals</h1>
        </div>
        <div class="details-content">
            <iframe src="<%= gioco.getTrailer() %>" title="<%= gioco.getTitolo() %> - Trailer" frameborder="0"></iframe>
        </div>
    </div>
    <div class="game-separator"></div>
    <div class="details">
        <div class="headline">
            <h1 class="hs-3">About the game</h1>
        </div>
        <div class="details-content">
            <p class="text"><%= gioco.getDescrizione() %></p>
            <div class="game-separator"></div>
            <p class="text">
                Data di uscita: <span style="color: white; font-weight: bold"><%= new SimpleDateFormat("dd MMMMMMMMMM yyyy").format(gioco.getData_uscita()) %></span>
            </p>
            <% if (generi != null && generi.size() > 0) {%>
                <p class="text">
                    Generi:
                    <span style="color: white; font-weight: bold">
                        <% for (int i = 0; i < generi.size(); i++) {%>
                            <%= (i + 1 == generi.size()) ? generi.get(i).getNome_genere() : generi.get(i).getNome_genere() + ", " %>
                        <% }%>
                    </span>
                </p>
            <% } %>
        </div>
    </div>
</div>