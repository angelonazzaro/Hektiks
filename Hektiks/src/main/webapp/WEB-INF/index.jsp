<%--
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
    <% }%>
</div>

<!-- page content end -->

<%@ include file="../templates/footer.jsp" %>