<%-- 
    Document   : index
    Created on : Dec 20, 2013, 1:16:54 PM
    Author     : koxa
--%>

<%@page import="model.db.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.messages.getString("index.web.library")}</title>
    </head>
    <body>
        <jsp:include page="autentifiation.jsp" flush="true"/>
        <br>
        <a href="./BookController.do?command=viewAll">${sessionScope.messages.getString("index.view.all.books")}</a>
        <br>
    </body>
</html>