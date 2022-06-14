<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 26/04/2022
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<% String pagePath = (String) request.getAttribute("page"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/header.jsp" %>

<!-- page content start -->

<div class="wrapper">
    <div class="main-content">
        <% if (pagePath != null) { %>
        <jsp:include page="<%= pagePath %>" />
        <% } else { %>
        <% List<Gioco> giochiDelMomento = (List<Gioco>) request.getAttribute("giochiDelMomento"); %>
        <% List<Gioco> bestSellers = (List<Gioco>) request.getAttribute("bestSellers"); %>

        <div class="products-container">
            <div class="products-heading">
                <h1 class="hs-1">Giochi del Momento</h1>
                <a class="show-all-btn hs-5" href="#">Mostra tutti</a>
            </div>
            <div class="products-content">
                <% for (Gioco gioco : giochiDelMomento) { %>
                <% int percentuale_sconto = gioco.getPercentuale_sconto(); %>
                <div class="card">
                    <div class="card-header">
                        <a href="gioco?codice_gioco=<%= gioco.getCodice_gioco() %>"><img class="card-img" src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina"></a>
                        <% if (percentuale_sconto > 0) {%>
<%--                        <% if (sconto.getData_fine() == null || sconto.getData_fine().after(new Date()) || sconto.getData_fine().equals(new Date())) {%>--%>
                        <div class="discount text">-<%= percentuale_sconto %>%</div>
<%--                        <% } %>--%>
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
        <div class="separator">
            <div class="separator-content">
                <div class="feature-card">
                    <img src="<%= request.getContextPath() %>/assets/images/icons/icon-download.svg" alt="icon download">
                    <div class="feature-text">
                        <h5 class="hs-5">Super veloce</h5>
                        <p class="text">Download digitale instantaneo</p>
                    </div>
                </div>
                <div class="feature-card">
                    <img src="<%= request.getContextPath() %>/assets/images/icons/icon-secure.svg" alt="icon secure">
                    <div class="feature-text">
                        <h5 class="hs-5">Affidabile & sicuro</h5>
                        <p class="text">Più di 10,000 giochi</p>
                    </div>
                </div>
                <div class="feature-card">
                    <img src="<%= request.getContextPath() %>/assets/images/icons/icon-customer-support.svg" alt="icon customer support">
                    <div class="feature-text">
                        <h5 class="hs-5">Supporto clienti</h5>
                        <p class="text">Supporto 24/7</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="products-container">
            <div class="products-heading">
                <h1 class="hs-1">Top Sellers</h1>
                <a class="show-all-btn hs-5" href="#">Mostra tutti</a>
            </div>
            <div class="products-content">
                <% for (Gioco gioco : bestSellers) { %>
                <% int percentuale_sconto = gioco.getPercentuale_sconto(); %>
                <div class="card">
                    <div class="card-header">
                        <a href="gioco?codice_gioco=<%= gioco.getCodice_gioco() %>"><img class="card-img" src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina"></a>
                        <% if (percentuale_sconto > 0) {%>
                        <div class="discount text">-<%= percentuale_sconto %>%</div>
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
        <% }%>
    </div>
</div>

<!-- page content end -->

<%@ include file="../templates/footer.jsp" %>