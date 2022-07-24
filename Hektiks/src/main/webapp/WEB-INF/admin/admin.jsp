<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String partPath = (String) request.getAttribute("part"); %>

<% Logger.consoleLog(Logger.JSP, "ADMIN.JSP" + " - " + "partPath: " + partPath); %>

<div class="breadcrumb text">
    <ul class="user-links">
        <li class="user-link <%= partPath != null && partPath.contains("utenti") ? "active" : "" %>"><a
                <%--method: GET  AdminServlet part: utenti--%>
                href="<%= request.getContextPath() %>/admin?part=utenti">
            Utenti</a>
        </li>
        <li class="user-link <%= partPath != null && partPath.contains("prodotti") ? "active" : "" %>"><a
                <%--method: GET  AdminServlet part: prodotti--%>
                href="<%= request.getContextPath() %>/admin?part=prodotti">Giochi</a></li>
        <li class="user-link <%= partPath != null && partPath.contains("giftcards") ? "active" : "" %>"><a
                <%--method: GET  AdminServlet part: giftcards--%>
                href="<%= request.getContextPath() %>/admin?part=giftcards">GiftCards</a></li>
    </ul>
</div>
<%--include una tra: utenti.jsp/prodotti.jsp/giftcards.jsp --%>
<jsp:include page="<%= partPath %>"/>
