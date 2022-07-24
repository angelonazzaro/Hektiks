<%@ page import="Model.Utente.Utente" %>
<%@ page import="Model.Carrello.Carrello" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utente user = (Utente) session.getAttribute("user"); %>
<% Logger.consoleLog(Logger.JSP, "HEADER.JSP"); %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <link rel="icon" href="<%= request.getContextPath() %>/assets/images/icons/gamepad-solid.svg"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/libs/awesomeNotifications/style.css"/>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css">

    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>

    <title><%= request.getAttribute("title") %>
    </title>
</head>
<body>
<!-- header start -->
<header>
    <nav>
        <%--method: GET HomeServlet--%>
        <a href="<%= request.getContextPath() %>/" id="logo" class="hs-1">HEKTIKS</a>
        <div class="search-bar">
            <input type="text" class="form-control" placeholder="Cerca..."/>
            <span><i class="fas fa-search"></i></span>
        </div>
        <% if (user != null) { %>
        <% String profile_pic = user.getProfile_pic() != null ? request.getContextPath() + "/assets/uploads/users" + user.getProfile_pic() : request.getContextPath() + "/assets/uploads/users/avatar_placeholder.png"; %>
        <div id="login-container">
            <div id="user-info">
                <p class="text"><%= user.getUsername() %>
                </p>
                <p class="text" id="user-balance"><%= String.format("%.2f€", user.getSaldo()).replace(",", ".") %>
                </p>
            </div>
            <img src="<%= profile_pic %>" alt="profile pic" id="profile-pic">
            <div class="cart">
                <%--method: GET CarrelloServlet--%>
                <a href="<%= request.getContextPath() %>/carrello">
                    <span class="caret">
                        <% if (session.getAttribute("quantita_carrello") != null) { %>
                            <%= (session.getAttribute("quantita_carrello")) %>
                        <% } else { %>
                            0
                        <% } %>
                    </span>
                    <i class="fas fa-shopping-cart"></i>
                </a>
            </div>
        </div>
        <% } else { %>
        <div style="display: flex; align-items: center">
            <div class="cart">
                <%--method: GET CarrelloServlet--%>
                <a href="<%= request.getContextPath() %>/carrello">
                    <span class="caret">
                        <% if (session.getAttribute("quantita_carrello") != null) { %>
                            <%= (session.getAttribute("quantita_carrello")) %>
                        <% } else { %>
                            0
                        <% } %>
                    </span>
                    <i class="fas fa-shopping-cart"></i>
                </a>
            </div>
            <div id="burger">
                <span class="line"></span>
                <span class="line"></span>
                <span class="line"></span>
            </div>
        </div>
        <% } %>
    </nav>
</header>
    <% if (user == null) { %>
<div id="login-registration-section">
    <div class="login-registration-child" id="child-1">
        <div id="forms-container">
            <%--method="POST" -> HomeServlet--%>
            <form action="<%= request.getContextPath() %>/" method="POST" class="login-registration-form hide"
                  id="registration-form">
                <div class="form-header">
                    <h2>Registrazione</h2>
                </div>
                <div class="form-body">
                    <div class="row">
                        <input type="text" class="form-control" name="nome" placeholder="Nome" required="required">
                        <input type="text" class="form-control" name="cognome" placeholder="Cognome"
                               required="required">
                    </div>
                    <div class="row">
                        <input type="email" name="email" placeholder="Email" required="required" class="form-control">
                    </div>
                    <div class="row">
                        <div class="input-group-append">
                            <input type="password" name="password" placeholder="Password" required="required"
                                   class="form-control">
                            <span class="password-icon-js"><i class="fas fa-eye"></i></span>
                        </div>
                        <div class="input-group-append">
                            <input type="password" name="confirm-password" placeholder="Conferma Password"
                                   required="required" class="form-control">
                            <span class="password-icon-js"><i class="fas fa-eye"></i></span>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="action" value="register">
                <div class="form-footer">
                    <button type="submit" class="btn form-control-submit-btn">Registrati</button>
                    <br>
                    <p data-next-form="login" data-prev-form="registration">Sei già registrato? <a href="#" class="next-form-btn"
                    >Accedi.</a></p>
                </div>
            </form>
            <%--method: POST -> HomeServlet--%>
            <form action="<%= request.getContextPath() %>/" method="POST" class="login-registration-form active"
                  id="login-form">
                <div class="form-header">
                    <h2>Login</h2>
                </div>
                <div class="form-body">
                    <div class="row">
                        <input type="email" name="email" placeholder="Email" required="required" class="form-control">
                    </div>
                    <div class="row">
                        <div class="input-group-append">
                            <input type="password" name="password" placeholder="Password" required="required"
                                   class="form-control">
                            <span class="password-icon-js"><i class="fas fa-eye"></i></span>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="action" value="login">
                <div class="form-footer">
                    <button type="submit" class="btn form-control-submit-btn">Login</button>
                    <br>
                    <p data-next-form="registration" data-prev-form="login">Non hai un account? <a href="#"
                                                                                                   class="next-form-btn"
                    >Registrati.</a></p>
                </div>
            </form>
        </div>
    </div>
    <div class="login-registration-child" id="child-2"></div>
</div>
    <% } %>
<!-- header end -->