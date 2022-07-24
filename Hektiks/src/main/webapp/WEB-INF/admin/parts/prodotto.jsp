<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Model.Genere.Genere" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco_Genere.Gioco_Genere" %>
<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Gioco gioco = (Gioco) request.getAttribute("gioco"); %>
<% List<Gioco_Genere> gioco_generi = (List<Gioco_Genere>) request.getAttribute("gioco_generi"); %>
<% List<Genere> generi = (List<Genere>) request.getAttribute("generi"); %>
<% Logger.consoleLog(Logger.JSP, "PRODOTTO.JSP"); %>

<div class="settings-wrapper dashboard-container">
    <h1 class="hs-3">Form di <%= gioco == null ? "aggiunta: Gioco" : "modifica: " + gioco.getTitolo() %></h1>
    <%--method: POST  AdminServlet --%>
    <form action="<%= request.getContextPath() %>/admin" method="POST" id="form-settings" class="settings-container">
        <div class="form-body">
            <input type="hidden" name="action" value="<%= gioco == null ? "add" : "edit" %>">
            <input type="hidden" name="componente" value="prodotto">

            <%-- Input type hidden per passare i vecchi valori alla servlet--%>
            <input type="hidden" name="current-code" value="<%= gioco == null ? "" : gioco.getCodice_gioco() %>">
            <input type="hidden" name="current-titolo" value="<%= gioco == null ? "" : gioco.getTitolo() %>">
            <input type="hidden" name="current-descrizione" value="<%= gioco == null ? "" : gioco.getDescrizione() %>">
            <input type="hidden" name="current-trailer" value="<%= gioco == null ? "" : gioco.getTrailer() %>">
            <input type="hidden" name="current-data" value="<%= gioco == null ? "" : gioco.getData_uscita() %>">
            <input type="hidden" name="current-copertina" value="<%= gioco == null ? "" : gioco.getCopertina() %>">
            <input type="hidden" name="current-prezzo" value="<%= gioco == null ? "" : gioco.getPrezzo() %>">
            <input type="hidden" name="current-quantita" value="<%= gioco == null ? "" : gioco.getQuantita_disponibile() %>">
            <input type="hidden" name="current-sconto" value="<%= gioco == null ? "" : gioco.getPercentuale_sconto() %>">

            <div class="row">
                <fieldset class="no-border">
                    <legend class="text">Codice Gioco</legend>
                    <input type="text" class="form-control" name="codice" value="<%= gioco == null ? "" : gioco.getCodice_gioco() %>" placeholder="Codice" required minlength="6" maxlength="6">
                </fieldset>
                <fieldset class="no-border">
                    <legend class="text">Titolo</legend>
                    <input type="text" class="form-control" name="titolo" value="<%= gioco == null ? "" : gioco.getTitolo() %>" placeholder="Titolo" required>
                </fieldset>
            </div>

            <div class="row">
                <fieldset class="no-border">
                    <legend class="text">Descrizione</legend>
                    <textarea name="descrizione" required placeholder="Descrizione..." cols="30" rows="10" class="form-control"><%= gioco == null ? "" : gioco.getDescrizione() %></textarea>
                </fieldset>
            </div>

            <div class="row">
                <fieldset class="no-border">
                    <legend class="text">Trailer</legend>
                    <input type="text" name="trailer" placeholder="Trailer: Inserisci Link" required maxlength="2048" class="form-control" value="<%= gioco == null ? "" : gioco.getTrailer() %>">
                </fieldset>
                <fieldset class="no-border">
                    <legend class="text">Copertina</legend>
                    <input type="text" name="copertina" placeholder="Copertina: Inserisci Link" required maxlength="2048" class="form-control" value="<%= gioco == null ? "" : gioco.getCopertina() %>">
                </fieldset>
            </div>

            <div class="row">
                <fieldset class="no-border">
                    <legend class="text">Prezzo</legend>
                    <input type="number" name="prezzo" class="form-control" placeholder="Prezzo" min="0.01" step="any" required value="<%= gioco == null ? "" : gioco.getPrezzo() %>">
                </fieldset>
                <fieldset class="no-border">
                    <legend class="text">Quantità disponibile</legend>
                    <input type="number" name="quantita" class="form-control" placeholder="Quantità disponibile" min="0" required step="1" value="<%= gioco == null ? "" : gioco.getQuantita_disponibile() %>">
                </fieldset>
            </div>

            <div class="row">
                <fieldset class="no-border">
                    <legend class="text">Data di uscita</legend>
                    <input type="date" name="data_uscita" class="form-control"  required value="<%= gioco == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(gioco.getData_uscita())%>">
                </fieldset>
                <fieldset class="no-border">
                    <legend class="text">Percentuale sconto</legend>
                    <input type="number" name="sconto" class="form-control" placeholder="Sconto" step="any" max="100" min="0" value="<%= gioco == null ? "" : gioco.getPercentuale_sconto() %>">
                </fieldset>
            </div>

            <div class="row" id="multi-select-container">
                <fieldset class="no-border">
                    <legend class="text">Generi</legend>
                    <select name="generi[]" class="form-control" multiple id="select-generi">
                        <% if (gioco == null || gioco_generi.isEmpty()) {%>
                        <% for (Genere genere : generi) { %>
                        <option value="<%= genere.getNome_genere() %>"><%= genere.getNome_genere() %></option>
                        <% } %>
                        <% } else { %>
                        <% for (Genere genere : generi) { %>
                        <option value="<%= genere.getNome_genere() %>" <%= gioco_generi.stream().anyMatch(g -> g.getNome_genere().equals(genere.getNome_genere())) ? "selected" : "" %>><%= genere.getNome_genere() %></option>
                        <% } %>
                        <% } %>
                    </select>
                </fieldset>
            </div>
        </div>
        <div class="form-footer">
            <button type="submit" class="btn" style="width: 100%">Invia</button>
        </div>
    </form>
</div>