<%-- 
    Document   : autentifiation
    Created on : Dec 26, 2013, 1:15:58 AM
    Author     : koxa
--%>

<%@page import="model.db.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="userGreatingsTld" prefix="loantaglib"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body> 
        <div style="background: #ccc; overflow: hidden;">
            <div style="display:inline-block; float: left;">
                <loantaglib:hello user="${sessionScope.user}"/>
            </div>
            <div style="display:inline-block; float: right">
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <a href="./logout.do">${sessionScope.messages.getString("autentification.logout")}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="./RegisterController.do">${sessionScope.messages.getString("autentification.register")}</a>
                        <a href="./login.do">${sessionScope.messages.getString("autentification.login")}</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div style="display:inline-block; float: right;">
                <form action="./BookController.do?command=changeLanguage" method="post">
                    <select name="language">
                        <option value="en">English</option>
                        <option value="ru">Русский</option>
                    </select>
                    <input type="submit" value="${sessionScope.messages.getString("autentification.submit")}">
                </form>
            </div>
        </div>
        <br>
    </body>
</html>
