<%-- 
    Document   : list
    Created on : 12.05.2011, 11:39:58
    Author     : Администратор
--%>

<%@page import="model.db.entities.BookCatalogueItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.messages.getString("books_list.title")}</title>
    </head>
    <body>
        <jsp:include page="autentifiation.jsp" flush="true"/>
        <br>
        <table bordercolor="black" border="2" width="100%">
            <tr>
                <td width="10%" valign="top">
                    <jsp:include page="menu.jsp" flush="true"/>
                </td>
                <td valign="top">
                    <table>
                        <tr>
                            <td>${sessionScope.messages.getString("books_list.book")}</td>
                            <td>${sessionScope.messages.getString("books_list.avaliable")}</td>
                            <td>${sessionScope.messages.getString("books_list.action")}</td>
                        </tr>
                        <c:forEach var="s" items="${requestScope.books}">
                            <tr>
                                <td><a href="./BookController.do?command=viewDetails&isbn=${s.getBook().getIsbn()}">${s.getBook().getTitle()}</a></td>
                                <td>${s.getAvaliable()}</td>
                                <td><a href="./BookController.do?command=addToCart&isbn=${s.getBook().getIsbn()}">${sessionScope.messages.getString("books_list.add.to.cart")}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td width="20%" valign="top">
                    <jsp:include page="cart.jsp" flush="true"/>
                </td>
            </tr>
        </table>
    </body>
</html>
