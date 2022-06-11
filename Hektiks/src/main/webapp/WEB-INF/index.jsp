<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="Model.Sconto.Sconto" %>
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
    <% if (pagePath != null) { %>
    <jsp:include page="<%= pagePath %>" />
    <% } else { %>
        <% List<Gioco> giochiDelMomento = (List<Gioco>) request.getAttribute("giochiDelMomento"); %>
        <% List<Gioco> bestSellers = (List<Gioco>) request.getAttribute("bestSellers"); %>

        <div class="main-content">
            <div class="products-container">
                <div class="products-heading">
                    <h1 class="hs-1">Giochi del Momento</h1>
                    <a class="show-all-btn hs-5" href="#">Mostra tutti</a>
                </div>
                <div class="products-content">
                    <% for (Gioco gioco : giochiDelMomento) { %>
                    <% Sconto sconto = (Sconto) gioco.getJoin().get(0); %>
                        <div class="card">
                            <div class="card-header">
                                <a href="#"><img class="card-img" src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina"></a>
                                <% if (sconto != null && sconto.getCodice_sconto() != null && sconto.getPercentuale() > 0) {%>
                                    <% if (sconto.getData_fine() == null || sconto.getData_fine().after(new Date()) || sconto.getData_fine().equals(new Date())) {%>
                                        <div class="discount text">-<%= sconto.getPercentuale() %>%</div>
                                    <% } %>
                                <% } %>
                            </div>
                            <div class="card-body">
                                <div class="name text">
                                    <%= gioco.getTitolo() %>
                                </div>
                                <div class="price hs-4">
                                    <% if (sconto != null && sconto.getCodice_sconto() != null && sconto.getPercentuale() > 0) {%>
                                        <% if (sconto.getData_fine() == null || sconto.getData_fine().after(new Date()) || sconto.getData_fine().equals(new Date())) {%>
                                            <%= String.format("%.2f", gioco.getPrezzo() - ((gioco.getPrezzo()) * sconto.getPercentuale()) / 100).replace(",", ".") %>€
                                        <% } %>
                                    <% } else { %>
                                        <%= String.format("%.2f", gioco.getPrezzo()).replace(",", ".") %>€
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
                    <% Sconto sconto = (Sconto) gioco.getJoin().get(0); %>
                    <div class="card">
                        <div class="card-header">
                            <a href="#"><img class="card-img" src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina"></a>
                            <% if (sconto != null && sconto.getCodice_sconto() != null && sconto.getPercentuale() > 0) {%>
                                <% if (sconto.getData_fine() == null || sconto.getData_fine().after(new Date()) || sconto.getData_fine().equals(new Date())) {%>
                                    <div class="discount text">-<%= sconto.getPercentuale() %>%</div>
                                <% } %>
                            <% } %>
                        </div>
                        <div class="card-body">
                            <div class="name text">
                                <%= gioco.getTitolo() %>
                            </div>
                            <div class="price hs-4">
                                <% if (sconto != null && sconto.getCodice_sconto() != null && sconto.getPercentuale() > 0) {%>
                                <% if (sconto.getData_fine() == null || sconto.getData_fine().after(new Date()) || sconto.getData_fine().equals(new Date())) {%>
                                <%= String.format("%.2f", gioco.getPrezzo() - ((gioco.getPrezzo()) * sconto.getPercentuale()) / 100).replace(",", ".") %>€
                                <% } %>
                                <% } else { %>
                                <%= String.format("%.2f", gioco.getPrezzo()).replace(",", ".") %>€
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
            <div class="separator" id="telegram-separator">
                <div class="separator-content" \>
                    <img src="<%= request.getContextPath() %>/assets/images/icons/telegram.svg" alt="telegram">
                    <div style="text-align: center; padding: 0 1.2rem;">
                        <h2 class="hs-5">Non perderti nessuna offerta o promozione!</h2>
                        <p class="text" style="color: var(--light-grey); margin-top: 0.2rem">Sii il primo a ricevere offerte e promozioni!</p>
                    </div>
                    <a href="https://t.me/WarehouseDealsItalia" target="_blank" class="btn">Unisciti</a>
                </div>
            </div>
        </div>
    <% }%>
</div>

<!-- page content end -->

<%@ include file="../templates/footer.jsp" %>