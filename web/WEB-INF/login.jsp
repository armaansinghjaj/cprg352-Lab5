<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action="login">
            <label>Username:</label>
            <input type="text" name="username" value="${user}">
            <br>
            <label>Password:</label>
            <input type="password" name="password" value="${password}">
            <br>
            <input type="submit" value="Log in">
        </form>
        <c:if test="${inputError == true}">
            <p>Not a valid input. Please try again.</p>
        </c:if>
        <c:if test="${sessionLogout == true}">
            <p>You have successfully logged out.</p>
        </c:if>
    </body>
</html>
