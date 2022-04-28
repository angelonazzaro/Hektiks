<%@ page import="Model.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 26/04/2022
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utente user = (Utente) session.getAttribute("user"); %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <link rel="icon" href="../assets/images/icons/gamepad-solid.svg"/>
    <link rel="stylesheet" href="../assets/libs/bootstrap-5.0.2-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/style.css"/>

    <title><%= request.getAttribute("page-title") %></title>
</head>
<body>
<!-- header start -->
<header>
    <nav>
        <h1 id="logo" class="front"><a href="#">Hektiks</a></h1>
        <div id="burger" class="front">
            <span class="line"></span>
            <span class="line"></span>
            <span class="line"></span>
        </div>
    </nav>
</header>
<% if (user == null) { %>
<div id="login-register-container" class="active">
    <div id="login-register__form-container">
        <form id="registration-form" class="login-register-form" action="#" method="POST">
            <h2 class="mb-4">Registrazione</h2>
            <div class="form-group row">
                <div class="col-md-6 col-sm-12">
                    <input type="text" name="nome" class="form-control-nb" placeholder="Nome:" required>
                </div>
                <div class="col-md-6 col-sm-12">
                    <input type="text" name="cognome" class="form-control-nb" placeholder="Cognome:" required>
                </div>
            </div>
            <div class="form-group col-12">
                <input type="email" name="email" class="form-control-nb" placeholder="Email:" required>
            </div>
            <div class="form-group row">
                <div class="col-md-6 col-sm-12">
                    <div class="input-group-nb">
                        <input type="password" name="password" class="form-control-nb" placeholder="Password:" required>
                        <i class="fas fa-eye icon"></i>
                    </div>
                </div>
                <div class="col-md-6 col-sm-12">
                    <div class="input-group-nb">
                        <input type="password" name="conferma-password" class="form-control-nb"
                               placeholder="Conferma Password:" required>
                        <i class="fas fa-eye icon"></i>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn submit-btn">Invia</button>
            <button class="btn-form next mt-5" type="button">Hai già un'account? Accedi
                <span>&#187;</span></button>
        </form>
        <form id="login-form" class="login-register-form hidden" action="#" method="POST">
            <h2 class="mb-4">Log in</h2>
            <div class="form-group col-12">
                <input type="email" name="email" class="form-control-nb" placeholder="Email:" required>
            </div>
            <div class="form-group col-12">
                <div class="input-group-nb">
                    <input type="password" name="password" class="form-control-nb" placeholder="Password:" required>
                    <i class="fas fa-eye icon"></i>
                </div>
            </div>
            <button type="submit" class="btn submit-btn">Accedi</button>
            <button class="btn-form previous mt-5" type="button">Non hai un'account? Registrati
                <span>&#171;</span></button>
        </form>
    </div>
    <div id="login-register__wallpaper"></div>
</div>
<% } %>
<!-- header end -->
</body>
</html>
