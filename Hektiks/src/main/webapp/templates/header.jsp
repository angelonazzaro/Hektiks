<%@ page import="Model.Utente.Utente" %><%--
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

    <title><%= request.getAttribute("title") %>
    </title>
</head>
<body>
<!-- header start -->
<header>
    <nav>
        <div class="flex">
            <div id="cart">
                <a href="#"><i class="fas fa-shopping-cart"></i></a>
            </div>
            <div id="burger">
                <span class="line"></span>
                <span class="line"></span>
                <span class="line"></span>
            </div>
        </div>
    </nav>
</header>

<% if (user == null) { %>
<section id="login-registration">
    <div id="login-registration__form-container">
        <form action="" method="POST" id="registration-form" class="login-registration-form show">
            <h2 class="hs-1">Registrazione</h2>
            <div class="row">
                <div class="form-group">
                    <input type="text" name="nome" maxlength="35" placeholder="Nome:" class="form-control" required>
                    <input type="text" name="cognome" maxlength="35" placeholder="Cognome:" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <input type="email" name="email" maxlength="320" placeholder="Email:" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="half">
                        <div class="input-group-append">
                            <input type="password" name="password" placeholder="Password:" class="form-control" required>
                            <span><i class="fas fa-eye"></i></span>
                        </div>
                    </div>
                    <div class="half">
                        <div class="input-group-append">
                            <input type="password" name="cnf-pwd" placeholder="Conferma Password:" class="form-control">
                            <span><i class="fas fa-eye"></i></span>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="action" value="register">
            <button type="submit" class="btn btn-submit flex align-center justify-center"><span>Invia</span></button>
            <div class="login-registration-form__btn" data-form="login">Hai già un account? Accedi.</div>
        </form>

        <form action="" method="POST" id="login-form" class="login-registration-form hide">
            <h2 class="hs-1">Log in</h2>
            <div class="row">
                <div class="form-group">
                    <input type="email" name="email" maxlength="320" placeholder="Email:" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="input-group-append">
                        <input type="password" name="password" placeholder="Password:" class="form-control" required>
                        <span><i class="fas fa-eye"></i></span>
                    </div>
                </div>
            </div>
            <input type="hidden" name="action" value="login">
            <button type="submit" class="btn btn-submit flex align-center justify-center"><span>Invia</span></button>
            <div class="login-registration-form__btn" data-form="registration">Non hai un account? Registrati.</div>
        </form>
    </div>
    <div id="login-registration__wallpaper"></div>
</section>
<% } %>