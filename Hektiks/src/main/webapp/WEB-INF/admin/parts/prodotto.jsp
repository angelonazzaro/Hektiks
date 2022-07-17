<%@ page import="Model.Gioco.Gioco" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Model.Genere.Genere" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco_Genere.Gioco_Genere" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 16/07/2022
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Gioco gioco = (Gioco) request.getAttribute("gioco"); %>
<% List<Gioco_Genere> gioco_generi = (List<Gioco_Genere>) request.getAttribute("gioco_generi"); %>
<% List<Genere> generi = (List<Genere>) request.getAttribute("generi"); %>

<div class="settings-wrapper dashboard-container">
    <h1 class="hs-3">Form di <%= gioco == null ? "aggiunta: Gioco" : "modifica: " + gioco.getTitolo() %></h1>
    <form action="<%= request.getContextPath() %>/admin" method="POST" id="form-settings" class="settings-container">
        <div class="form-body">
            <input type="hidden" name="action" value="<%= gioco == null ? "add" : "edit" %>">
            <input type="hidden" name="current-code" value="<%= gioco == null ? "" : gioco.getCodice_gioco() %>">
            <input type="hidden" name="componente" value="prodotto">

            <div class="row">
                <input type="text" class="form-control" name="codice" value="<%= gioco == null ? "" : gioco.getCodice_gioco() %>" placeholder="Codice" required minlength="6" maxlength="6">
                <input type="text" class="form-control" name="titolo" value="<%= gioco == null ? "" : gioco.getTitolo() %>" placeholder="Titolo" required>
            </div>
            <div class="row">
                <textarea name="descrizione" required placeholder="Descrizione..." cols="30" rows="10" class="form-control"><%= gioco == null ? "" : gioco.getDescrizione() %></textarea>
            </div>
            <div class="row">
                <input type="text" name="trailer" placeholder="Trailer: Inserisci Link" required maxlength="2048" class="form-control" value="<%= gioco == null ? "" : gioco.getTrailer() %>">
                <input type="text" name="copertina" placeholder="Copertina: Inserisci Link" required maxlength="2048" class="form-control" value="<%= gioco == null ? "" : gioco.getCopertina() %>">
            </div>
            <div class="row">
                <input type="number" name="prezzo" class="form-control" placeholder="Prezzo" min="0.01" step="any" required value="<%= gioco == null ? "" : gioco.getPrezzo() %>">
                <input type="number" name="quantita" class="form-control" placeholder="Quantità disponibile" min="0" required step="1" value="<%= gioco == null ? "" : gioco.getQuantita_disponibile() %>">
            </div>
            <div class="row">
                <input type="date" name="data_uscita" class="form-control" required value="<%= gioco == null ? "" : new SimpleDateFormat("y-M-d").format(gioco.getData_uscita())%>">
                <input type="number" name="sconto" class="form-control" placeholder="Sconto" step="any" max="100" min="1">
            </div>
            <div class="row">
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
            </div>
        </div>
        <div class="form-footer">
            <button type="submit" class="btn" style="width: 100%">Invia</button>
        </div>
    </form>
</div>
