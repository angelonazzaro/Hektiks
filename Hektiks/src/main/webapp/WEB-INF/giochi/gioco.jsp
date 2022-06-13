<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="Model.Sconto.Sconto" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 12/06/2022
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Gioco gioco = (Gioco) request.getAttribute("gioco"); %>
<% Sconto sconto = gioco.getJoin() != null ? (Sconto) gioco.getJoin().get(0) : null; %>

<div class="game-info-cards-container">
    <div class="game-card card">
        <div class="game-card-header">
            <img src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina" class="card-img">
        </div>
    </div>
    <div class="game-card card">
        <div class="game-card-header">
            <h1 class="hs-3"><%= gioco.getTitolo() %>
            </h1>
            <div class="game-card-header-info">
                <p class="game-card-info text">
                    <% if (gioco.getQuantita_disponibile() > 0) { %>
                        <span style="color: green">
                            <i class="fas fa-check"></i>
                        </span>
                        Disponibile
                    <% } else { %>
                        <span style="color: red">
                            <i class="fas fa-times"></i>
                        </span>
                        Non disponibile
                    <% } %>
                </p>
            </div>
        </div>
        <div class="game-card-body">
            <div class="game-card-price">
                <% if (sconto != null && sconto.getCodice_sconto() != null && sconto.getPercentuale() > 0) { %>
                    <% if (sconto.getData_fine() == null || sconto.getData_fine().after(new Date()) || sconto.getData_fine().equals(new Date())) {%>
                        <div class="original-price text">
                            <%= String.format("%.2f", gioco.getPrezzo()).replace(",", ".") %>€
                        </div>
                        <div class="game-card-discount text">
                            -<%= sconto.getPercentuale() %>%
                        </div>
                        <div class="price hs-2">
                            <%= String.format("%.2f", gioco.getPrezzo() - ((gioco.getPrezzo()) * sconto.getPercentuale()) / 100).replace(",", ".") %>€
                        </div>
                    <% } else { %>
                        <div class="price hs-2">
                            <%= String.format("%.2f", gioco.getPrezzo()).replace(",", ".") %>€
                        </div>
                    <% } %>
                <% } else { %>
                <div class="price hs-2">
                    <%= String.format("%.2f", gioco.getPrezzo()).replace(",", ".") %>€
                </div>
                <% } %>
            </div>
            <div class="game-card-buy">
                <a title="Aggiungi al Carrello" class="btn">
                    <i class="fas fa-heart"></i>
                </a>
                <button class="btn">
                    Compra
                </button>
            </div>
        </div>
    </div>
</div>