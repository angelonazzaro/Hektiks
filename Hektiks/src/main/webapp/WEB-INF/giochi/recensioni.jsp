<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="Model.Recensione.Recensione" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Utente.Utente" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Gioco gioco = (Gioco) request.getAttribute("gioco"); %>
<% List<Recensione> recensioni = (List<Recensione>) request.getAttribute("recensioni"); %>
<% Logger.consoleLog(Logger.JSP, "RECENSIONI.JSP"); %>

<div class="game-presentation">
    <div class="card" style="cursor: default !important;">
        <div class="banner">
            <img src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina">
        </div>
        <div class="card-body">
            <%--method: GET  GiocoServlet--%>
            <a href="<%= request.getContextPath() %>/gioco?codice_gioco=<%= gioco.getCodice_gioco() %>" class="btn review-btn"><i class="fas fa-angle-left"></i> Torna indietro</a>
            <a class="btn review-btn" data-toggle="modal" data-target="#recensioni-modal">Valuta questo gioco! <i class="fas fa-pen-alt"></i></a>
        </div>
    </div>
    <div class="game-separator"></div>
    <div class="details">
        <div class="headline">
            <!-- è il voto medio delle recensioni. se il voto è un intero, non mostro cifre decimali  -->
            <!-- se è un decimale, mostro solo la prima cifra decimale. Es. se il voto medio è 4.75 mostro solo 4.7  -->

            <!--  Bad rating, mid-rating e good-rating servono per stabilire il colore del cerchio intorno al voto -->
            <div class="rating-info">
                <% double votoGenerale = recensioni.stream().mapToDouble(Recensione::getVoto).average().orElse(0); %>
                <% String rating = ""; %>
                <% if (votoGenerale < 2) { rating = "bad-rating"; %>
                <% } else if (votoGenerale > 2 && votoGenerale < 3) { rating = "mid-rating"; %>
                <% } else { rating = "good-rating"; } %>
                <div class="rating <%= rating %> text">
                    <% if (votoGenerale % 1 != 0)  {%>
                    <%= String.format("%.1f", votoGenerale).replace(",", ".") %>
                    <% } else { %>
                    <%= String.format("%.0f", votoGenerale) %>
                    <% } %>
                </div>
                <div class="info text">
                    <%= gioco.getTitolo() %>
                </div>
            </div>
        </div>
        <div class="review-container">
            <% for (Recensione recensione : recensioni) { %>
                <% Utente utente = (Utente) recensione.getJoin().get(0); %>
                <% String profile_pic = utente.getProfile_pic() != null ? request.getContextPath() + "/assets/uploads/users" + utente.getProfile_pic() : request.getContextPath() + "/assets/uploads/users/avatar_placeholder.png"; %>

                <div class="review">
                    <div class="review-header">
                        <a class="user-banner" title="<%= utente.getUsername() %>">
                            <img
                                    src="<%= profile_pic %>"
                                    alt="<%= utente.getUsername() %> immagine profilo"
                            />
                        </a>
                        <div class="review-stars">
                            <% double voto_rimanente = recensione.getVoto(); %>
                            <% for (int i = 1; i <= recensione.getVoto(); i++) {%>
                            <i class="fas fa-star"></i>
                            <% voto_rimanente -= 1; %>
                            <% } %>
                            <% if (voto_rimanente > 0) { %>
                            <i class="fas fa-star-half"></i>
                            <% } %>
                        </div>
                    </div>
                    <div class="review-body">
                        <p class="text">
                            <%= recensione.getDescrizione() %>
                        </p>
                    </div>
                    <hr />
                    <div class="review-footer">
                        <p class="text"><%= new SimpleDateFormat("dd MMMMMMMMMM yyyy HH:mm:ss").format(recensione.getData_ora_pubblicazione()) %></p>
                    </div>
                </div>
            <% } %>
        </div>
    </div>
</div>

<%@ include file="../includes/recensioni_modal.jsp" %>