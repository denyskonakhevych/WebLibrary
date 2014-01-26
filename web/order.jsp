<%-- 
    Document   : order
    Created on : Jan 6, 2014, 11:03:18 AM
    Author     : koxa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <body>
        <jsp:include page="cart.jsp" flush="true"/>
        <c:set var="booksInShoppingCart" value="${sessionScope.shoppingCart.get().getBooks()}" scope="session"></c:set>
        <br>
        <c:choose>
            <c:when test="${booksInShoppingCart != null && !booksInShoppingCart.isEmpty()}">
                <form action="./CartController.do?command=executeOrder" method="post">
                    <select name="loanType">
                        <option value="readingRoom">${sessionScope.messages.getString("order.room")}</option>
                        <option value="readingCard">${sessionScope.messages.getString("order.card")}</option>
                    </select>
                    <input type="submit" value="Submit">
                </form>
            </c:when>
            <c:otherwise>
                ${sessionScope.messages.getString("order.back.to.the")} <a href="./BookController.do?command=viewAll">${sessionScope.messages.getString("order.main")}</a>
            </c:otherwise>
        </c:choose>
    </body>
</html>
