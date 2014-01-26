<%-- 
    Document   : cart
    Created on : Dec 25, 2013, 10:10:23 PM
    Author     : koxa
--%>

<%@page import="java.util.ResourceBundle"%>
<%@page import="model.db.entities.Book"%>
<%@page import="model.server.cart.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:set var="booksInShoppingCart" value="${sessionScope.shoppingCart.get().getBooks()}" scope="session"></c:set>
        <c:choose>
            <c:when test="${booksInShoppingCart != null && !booksInShoppingCart.isEmpty()}">
                <table>
                    <tr>
                        <td>${sessionScope.messages.getString("cart.book")}</td>
                        <td>${sessionScope.messages.getString("cart.action")}</td>
                    </tr>
                    <c:forEach var="cartItem" items="${booksInShoppingCart}">
                        <tr>
                            <td><a href="./BookController.do?command=viewDetails&isbn=${cartItem.getIsbn()}">${cartItem.getTitle()}</a></td>
                            <td><a href="./BookController.do?command=removeFromCart&isbn=${cartItem.getIsbn()}">${sessionScope.messages.getString("cart.remove.from.cart")}</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <a href="./CartController.do?command=submitOrder">${sessionScope.messages.getString("cart.order")}</a>
            </c:when>
            <c:otherwise>
                <c:out value="<%=((ResourceBundle)(((HttpSession)request.getSession()).getAttribute("messages"))).getString("cart.cart.is.empty")%>"/>
            </c:otherwise>
        </c:choose>
    </body>
</html>
