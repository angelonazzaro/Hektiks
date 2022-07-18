<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 16/06/2022
  Time: 09:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Gioco> giochi = (List<Gioco>) request.getAttribute("giochi"); %>

<div class="search-bar">
    <input type="text" class="form-control" placeholder="Cerca..."/>
    <span><i class="fas fa-search"></i></span>
</div>

<div class="products-container">
    <div class="products-content">
        <% for (Gioco gioco : giochi) { %>
            <% double percentuale_sconto = gioco.getPercentuale_sconto(); %>
            <div class="card">
                <div class="card-header">
                    <a href="gioco?codice_gioco=<%= gioco.getCodice_gioco() %>"><img class="card-img" src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina"></a>
                    <% if (percentuale_sconto > 0) {%>
                    <div class="discount text">-<%= String.format("%.2f", percentuale_sconto).replace(",", ".") %>%</div>
                    <% } %>
                </div>
                <div class="card-body">
                    <div class="name text">
                        <%= gioco.getTitolo() %>
                    </div>
                    <div class="price hs-4">
                        <% if (gioco.getPrezzo() > 0) { %>
                            <% if (percentuale_sconto > 0) {%>
                                <%= String.format("%.2f€", gioco.getPrezzo() - ((gioco.getPrezzo()) * percentuale_sconto) / 100).replace(",", ".") %>
                            <% } else { %>
                                <%= String.format("%.2f€", gioco.getPrezzo()).replace(",", ".") %>
                            <% } %>
                        <% } else { %>
                            Gratis
                        <% } %>
                    </div>
                </div>
            </div>
        <% } %>
    </div>
</div>