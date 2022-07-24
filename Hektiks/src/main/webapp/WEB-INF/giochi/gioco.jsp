<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco_Genere.Gioco_Genere" %>
<%@ page import="Model.Recensione.Recensione" %>
<%@ page import="Model.Utente.Utente" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Gioco gioco = (Gioco) request.getAttribute("gioco"); %>
<% List<Gioco_Genere> generi = (List<Gioco_Genere>) request.getAttribute("generi"); %>
<% List<Recensione> recensioni = (List<Recensione>) request.getAttribute("recensioni"); %>
<% Logger.consoleLog(Logger.JSP, "GIOCO.JSP"); %>

<div class="game-presentation">
    <div class="banner">
        <img src="<%= gioco.getCopertina() %>" alt="<%= gioco.getTitolo() %> - Copertina">
    </div>
    <%--method: POST AcquistoServlet--%>
    <form action="<%= request.getContextPath() %>/acquisto" method="POST">
        <div class="game-card" data-code="<%= gioco.getCodice_gioco() %>" data-title="<%= gioco.getTitolo() %>" data-price="<%= gioco.getPrezzo() %>" data-discount="<%= gioco.getPercentuale_sconto() %>">
            <div class="title">
                <h1 class="hs-3"><%= gioco.getTitolo() %></h1>
            </div>
            <div class="subinfos">
                <p class="subinfo text <%= gioco.getQuantita_disponibile() > 0 ? "tick" : "cross" %>">
                    <%= gioco.getQuantita_disponibile() > 0 ? "" : " Non" %> Disponibile
                </p>
            </div>
            <div class="amount">
                <% if (gioco.getPrezzo() == 0) { %>
                    <p class="current-amount hs-3 free-to-play">Free to play</p>
                <% } else if (gioco.getPercentuale_sconto() > 0) {%>
                    <p class="original-amount text"><%= String.format("%.2f€", gioco.getPrezzo()).replace(",", ".") %></p>
                    <p class="discount-amount text ">-<%= String.format("%.2f", gioco.getPercentuale_sconto()).replace(",", ".") %>%</p>
                    <p class="current-amount hs-3"><%=  String.format("%.2f€", gioco.getPrezzo() - ((gioco.getPrezzo() * gioco.getPercentuale_sconto() / 100))).replace(",", ".") %></p>
                <% } else { %>
                    <p class="current-amount hs-3"><%= String.format("%.2f€", gioco.getPrezzo()).replace(",", ".") %></p>
                <% } %>
            </div>
            <% if (gioco.getQuantita_disponibile() > 0) {%>
                <input type="hidden" name="from" value="gioco">
                <input type="hidden" name="codice_gioco" value="<%= gioco.getCodice_gioco() %>">
                <div class="actions">
                    <a class="btn" id="add-to-cart-btn" data-quantity="<%= gioco.getQuantita_disponibile() %>"><span><i class="fas fa-shopping-cart"></i></span></a>
                    <button class="btn" type="submit">Acquista</button>
                </div>
                <p class="text" id="p-error" style="color: red"></p>
            <% } %>
        </div>
    </form>

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
    <div class="game-separator"></div>
    <div class="details">
        <div class="headline">
            <h1 class="hs-3">Recensioni</h1>
        </div>
        <div class="details-content">
            <% if (recensioni == null || recensioni.size() == 0) {%>
                <div class="no-review-container">
                    <h2 class="hs-3">Non ci sono recensioni.</h2>
                    <a class="btn" data-toggle="modal" data-target="#recensioni-modal">Valuta questo gioco! <i class="fas fa-pen-alt"></i></a>
                </div>
            <% } else { %>
                <div class="game-rating">
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
                            Valutazione Gioco <br>
                            basata su <u><%= recensioni.size() %> recension<%= recensioni.size() > 1 ? "i" : "e" %></u>
                        </div>
                    </div>
                    <a class="btn" data-toggle="modal" data-target="#recensioni-modal"
                    >Valuta questo gioco!
                        <i class="fas fa-pen-alt"></i
                        ></a>
                </div>
                <div class="review-container">
                    <% int size = Math.min(recensioni.size(), 6); %>
                    <% for (int i = 0; i < size; i++) { %>
                        <% Utente utente = (Utente) recensioni.get(i).getJoin().get(0); %>
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
                                        <% double voto_rimanente = recensioni.get(i).getVoto(); %>
                                        <% for (int k = 1; k <= recensioni.get(i).getVoto(); k++) {%>
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
                                        <%= recensioni.get(i).getDescrizione() %>
                                    </p>
                                </div>
                                <hr />
                                <div class="review-footer">
                                    <p class="text"><%= new SimpleDateFormat("dd MMMMMMMMMM yyyy HH:mm:ss").format(recensioni.get(i).getData_ora_pubblicazione()) %></p>
                                </div>
                            </div>
                    <% } %>
                </div>
                <div class="btn-container">
                    <%--method: GET -> RecensioneServlet--%>
                    <a href="<%= request.getContextPath() %>/recensione?codice_gioco=<%= gioco.getCodice_gioco() %>" class="btn">Visualizza più recensioni</a>
                </div>
            <% } %>
        </div>
    </div>
</div>

<%@ include file="../includes/recensioni_modal.jsp" %>