<%@ page import="java.util.List" %>
<%@ page import="Model.Gioco.Gioco" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 26/04/2022
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<% String pagePath = (String) request.getAttribute("page"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../templates/header.jsp" %>

<% List<Gioco> giochi = (List<Gioco>) request.getAttribute("giochi"); %>

<!-- page content start -->

<div class="wrapper">
    <% if (pagePath != null) { %>
    <jsp:include page="<%= pagePath %>" />
    <% } else { %>
        <div class="main-content">
            <div class="products-container">
                <div class="products-heading">
                    <h1 class="hs-1">Trending</h1>
                    <a class="show-all-btn hs-5" href="#">Mostra tutti</a>
                </div>
                <div class="products-content">
                    <% for (Gioco gioco : giochi) { %>
                        <div class="card">
                            <div class="card-header">
                                <a href="#"><img class="card-img" src="<%= request.getContextPath() + "/assets/images/products/" +  gioco.getCopertina()%>" alt="<%= gioco.getTitolo() %> - Copertina"></a>
<%--                                <div class="discount"></div>--%>
                            </div>
                            <div class="card-body">
                                <div class="name text">
                                    <%= gioco.getTitolo() %>
                                </div>
                                <div class="price hs-4">
                                    <%= String.format("%.2f", gioco.getPrezzo()).replace(",", ".") %>€
                                </div>
                            </div>
                        </div>
                    <% } %>
                </div>
            </div>
        </div>
    <% }%>
</div>

<!-- page content end -->

<%@ include file="../templates/footer.jsp" %>