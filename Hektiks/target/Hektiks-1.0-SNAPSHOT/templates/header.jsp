<%@ page import="Model.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 26/04/2022
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utente utente = (Utente) session.getAttribute("utente"); %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <link rel="icon" href="<%= request.getContextPath() %>/assets/images/icons/gamepad-solid.svg"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css"/>

    <title><%= request.getAttribute("title") %>
    </title>
</head>
<body>
<!-- header start -->
<header>
    <nav>
        <div id="burger">
            <span class="line"></span>
            <span class="line"></span>
            <span class="line"></span>
        </div>
    </nav>
</header>

<% if (utente == null) { %>
<section id="login-registration">
    <div id="login-registration__form-container">
        <form action="#" method="POST" id="registration-form" class="login-registration-form show">
            <h2 class="hs-1">Registrazione</h2>
            <div class="row">
                <div class="form-group">
                    <input type="text" name="nome" maxlength="35" placeholder="Nome:" class="form-control">
                    <input type="text" name="cognome" maxlength="35" placeholder="Cognome:" class="form-control">
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <input type="email" name="email" maxlength="320" placeholder="Email:" class="form-control">
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <input type="password" name="password" placeholder="Password:" class="form-control">
                    <input type="password" name="cnf-pwd" placeholder="Conferma Password:" class="form-control">
                </div>
            </div>
            <button type="submit" class="btn btn-submit">Invia</button>
            <div class="login-registration-form__btn" data-form="login">Hai già un account? Accedi.</div>
        </form>

        <form action="#" method="POST" id="login-form" class="login-registration-form hide">
            <h2 class="hs-1">Log in</h2>
            <div class="row">
                <div class="form-group">
                    <input type="email" name="email" maxlength="320" placeholder="Email:" class="form-control">
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <input type="password" name="password" placeholder="Password:" class="form-control">
                </div>
            </div>
            <button type="submit" class="btn btn-submit">Invia</button>
            <div class="login-registration-form__btn" data-form="registration">Non hai un account? Registrati.</div>
        </form>
    </div>
    <div id="login-registration__wallpaper"></div>
</section>
<% } %>