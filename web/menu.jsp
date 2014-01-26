<%-- 
    Document   : menu
    Created on : Dec 27, 2013, 7:37:56 PM
    Author     : koxa
--%>

<%@page import="model.db.entities.Ganre"%>
<%@page import="model.db.entities.User"%>
<%@page import="model.db.entities.User.Role"%>
<%@page import="model.server.menu.Menu"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:set var="menuItems" value="${requestScope.menu.getMenuItems()}" scope="request"></c:set>
        <ul>
            <li>
                <b><a href="./BookController.do?command=viewAll">${sessionScope.messages.getString("menu.main")}</a></b>
            </li>
            <c:forEach var="menuItem" items="${menuItems}">
                <li>
                    <a href="./BookController.do?command=viewAll&category=${menuItem.getGanreId()}">${menuItem.getName()}</a>
                </li>
            </c:forEach>
            <c:choose>
                <c:when test="${sessionScope.user != null && sessionScope.user.isModerator()}">
                    <li>
                        <a href="./TaskController.do?command=viewAllOrderedLoans">${sessionScope.messages.getString("menu.ordered.for.card")}</a>
                    </li>
                    <li>
                        <a href="./TaskController.do?command=viewAllOrderedReadingRooms">${sessionScope.messages.getString("menu.ordered.for.rr")}</a>
                    </li>
                    <li>
                        <a href="./TaskController.do?command=viewAllTakenLoans">${sessionScope.messages.getString("menu.taken.for.card")}</a>
                    </li>
                    <li>
                        <a href="./TaskController.do?command=viewAllTakenReadingRooms">${sessionScope.messages.getString("menu.taken.for.rr")}</a>
                    </li>
                </c:when>
            </c:choose>
        </ul>
    </body>
</html>
