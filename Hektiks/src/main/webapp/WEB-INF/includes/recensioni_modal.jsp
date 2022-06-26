<%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 26/06/2022
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal" id="recensioni-modal" style="display: none">
    <div class="modal-header">
        <h4 class="modal-title hs-4">Scrivi la tua recensione</h4>
        <span class="close hs-4" data-target="#recensioni-modal">&times;</span>
    </div>
    <form action="<%= request.getContextPath() %>/recensione" style="width: 100%" id="recensioni-form" method="POST">
        <div class="modal-body">
            <textarea name="descrizione" placeholder="Facci sapere cosa pensi..." cols="30" rows="10"
                      class="form-control"></textarea>
            <input type="number" name="voto" step="0.5" min="0" max="5" placeholder="Quanto daresti a questo gioco?"
                   required id="" class="form-control">
            <input type="hidden" name="codiceGioco" value="<%= gioco.getCodice_gioco() %>">
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn">Lascia Recensione</button>
        </div>
    </form>
</div>