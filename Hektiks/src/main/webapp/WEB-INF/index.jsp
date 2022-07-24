<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.util.Arrays" %>

<% String pagePath = (String) request.getAttribute("page"); %>
<% String[] scripts = (String[]) request.getAttribute("scripts"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Logger.consoleLog(Logger.JSP, "INDEX.JSP" + " - " + "pagePath: " + pagePath + " - " + "scripts: " + Arrays.toString(scripts)); %>

<%@ include file="../templates/header.jsp" %>

<!-- pageParth = pagina che deve essere inclusa e visualizzata. Se è nulla, mostriamo il contenuto dell'homepage -->

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
                <%--method="GET" -> GiocoServlet --%>
                <a class="show-all-btn hs-5" href="<%= request.getContextPath() %>/gioco?action=show-all">Mostra tutti</a>
            </div>
            <div class="products-content">
                <% for (Gioco gioco : giochiDelMomento) { %>
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
                <a class="show-all-btn hs-5" href="<%= request.getContextPath() %>/gioco?action=show-all">Mostra tutti</a>
            </div>
            <div class="products-content">
                <% for (Gioco gioco : bestSellers) { %>
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
        <% }%>
    </div>
</div>

<!-- page content end -->

<%@ include file="../templates/footer.jsp" %>

<!-- Mostriamo le toast notification quando necessario -->
<% if (session != null && session.getAttribute("msg-error") != null) { %>
<script>
    notifier.alert("<%= session.getAttribute("msg-error") %>");
    <% session.removeAttribute("msg-error"); %>
</script>
<% } %>

<% if (session != null && session.getAttribute("msg-success") != null) { %>
<script>
    notifier.success("<%= session.getAttribute("msg-success") %>");
    <% session.removeAttribute("msg-success"); %>
</script>
<% } %>

<!-- Carichiamo gli scripts caratteristici di pagePath dopo aver caricato il footer siccome contiente tutti gli script delle librerie e puglin -->
<% if (scripts != null && scripts.length > 0) { %>
    <% for (String script : scripts) { %>
        <script src="<%= request.getContextPath() %>/assets/js/<%= script %>"></script>
    <% } %>
<% } %>