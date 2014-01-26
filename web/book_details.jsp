<%-- 
    Document   : list
    Created on : 12.05.2011, 11:39:58
    Author     : Администратор
--%>

<%@page import="model.db.entities.Book"%>
<%@page import="model.server.book.selected.SelectedBook"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table>
            <c:set var="selectedBook" value="${requestScope.selectedBook}" scope="request"></c:set>
            <tr>
                <!--<td>Title</td>-->
                <td>${sessionScope.messages.getString("book_details.title")}</td>
                <td>${selectedBook.getTitle()}</td>
            </tr>
            <tr>
                <td>${sessionScope.messages.getString("book_details.isbn")}</td>
                <td>${selectedBook.getIsbn()}</td>
            </tr>
            <tr>
                <td>${sessionScope.messages.getString("book_details.ganre")}</td>
                <td><a href="./BookController.do?command=viewAll&category=${selectedBook.getGanre().getGanreId()}">${selectedBook.getGanre().getName()}</a></td>
            </tr>
            <tr>
                <td>${sessionScope.messages.getString("book_details.description")}</td>
                <td>${selectedBook.getDescription()}</td>
            </tr>
            <tr>
                <td>${sessionScope.messages.getString("book_details.author")}</td>
                <td>${selectedBook.getAuthor()}</td>
            </tr>
        </table>
        <br>
    </body>
</html>
