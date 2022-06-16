<%@ page import="Model.Utente.Utente" %>
<%@ page import="Model.Carrello.Carrello" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 26/04/2022
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utente user = (Utente) session.getAttribute("user"); %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <link rel="icon" href="<%= request.getContextPath() %>/assets/images/icons/gamepad-solid.svg"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/libs/awesomeNotifications/style.css"/>

    <title><%= request.getAttribute("title") %>
    </title>
</head>
<body>
<!-- header start -->
<header>
    <nav>
        <a href="<%= request.getContextPath() %>/" id="logo" class="hs-1">HEKTIKS</a>
        <div class="search-bar">
            <input type="text" class="form-control" placeholder="Cerca..."/>
            <span><i class="fas fa-search"></i></span>
        </div>
        <div style="display: flex">
            <div id="cart">
                <a href="<%= request.getContextPath() %>/carrello">
                    <span class="caret">
                        <% if (session.getAttribute("carrello") != null) { %>
                            <%= ((List<Gioco>) session.getAttribute("carrello")).size() %>
                        <% } else { %>
                            0
                        <% } %>
                    </span>
                    <i class="fas fa-shopping-cart"></i>
                </a>
            </div>
            <% if (user == null) { %>
            <div id="burger">
                <span class="line"></span>
                <span class="line"></span>
                <span class="line"></span>
            </div>
            <% } %>
        </div>
    </nav>
</header>

    <% if (user == null) { %>
<div id="login-registration-section">
    <div class="login-registration-child" id="child-1">
        <div id="forms-container">
            <form action="<%= request.getContextPath() %>/" method="POST" class="login-registration-form active" id="registration-form">
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
                    <p>Sei già registrato? <a data-next-form="login" href="#" class="next-form-btn"
                                                                     data-prev-form="registration">Accedi.</a></p>
                </div>
            </form>
            <form action="<%= request.getContextPath() %>/" method="POST" class="login-registration-form hide" id="login-form">
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
                    <p>Non hai un account? <a data-next-form="registration" href="#" class="next-form-btn"
                                                                            data-prev-form="login">Registrati.</a></p>
                </div>
            </form>
        </div>
    </div>
    <div class="login-registration-child" id="child-2"></div>
</div>
    <% } %>
<!-- header end -->