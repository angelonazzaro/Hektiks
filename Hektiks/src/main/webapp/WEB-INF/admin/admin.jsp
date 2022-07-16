<%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 16/07/2022
  Time: 09:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String partPath = (String) request.getAttribute("part"); %>

<jsp:include page="<%= partPath %>" />
