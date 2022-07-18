<%--
  Created by IntelliJ IDEA.
  User: Panin
  Date: 30/04/2022
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>

<style>
    .wrapper {
        background-color: var(--section-bg);
        display: flex;
        justify-content: center;
        align-items: center;

    }

    .wrapper p {
        text-align: center;
        font-size: calc(3em + 1vmin);
        color: var(--orange);
        padding: 0 2rem;

    }
</style>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <div class="wrapper">
        <p>Whoopies! Si è verificato il seguente errore: <br>
            <%= request.getAttribute("jakarta.servlet.error.status_code") %>
            : <%= request.getAttribute("jakarta.servlet.error.message") %>
        </p>
    </div>
</body>

<html>