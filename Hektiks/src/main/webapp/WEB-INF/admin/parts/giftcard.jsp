
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="settings-wrapper dashboard-container">
    <h1 class="hs-3">Form di aggiunta: GiftCard</h1>
    <form action="<%= request.getContextPath() %>/admin" method="POST" id="form-settings" class="settings-container">
        <div class="form-body">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="componente" value="giftcard">

            <div class="row">
                <input type="text" class="form-control" name="codice" placeholder="Codice" required minlength="6"
                       maxlength="6">
            </div>
            <div class="row">
                <input type="number" name="importo" class="form-control" placeholder="Importo" step="any" min="0.01">
            </div>
        </div>
        <div class="form-footer">
            <button type="submit" class="btn" style="width: 100%">Invia</button>
        </div>
    </form>
</div>
