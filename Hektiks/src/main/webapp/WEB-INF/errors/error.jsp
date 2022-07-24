<%@ page import="Utils.Logger.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<% Logger.consoleLog(Logger.JSP, "ERROR.JSP"); %>

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
            <% if (request.getAttribute("jakarta.servlet.error.status_code") == null) { %>
                <%= "Risorsa non disponibile" %>
            <% } else { %>
            <%= request.getAttribute("jakarta.servlet.error.status_code") %>
            : <%= request.getAttribute("jakarta.servlet.error.message") %>
            <% } %>
        </p>
    </div>
</body>

<html>