<%-- 
    Document   : loginPage
    Created on : Dec 20, 2013, 1:27:26 PM
    Author     : koxa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.messages.getString("login.login.page")}</title>
    </head>
    <body>
        <span>${sessionScope.messages.getString("login.message")}</span>
        <span style="color:red"><%=request.getAttribute("aut_error") == null ? "" : "<br>" + request.getAttribute("aut_error")%></span>
        <form action="./login.do" method="POST">
            <input type='hidden' name='submitted' value='<%=request.getAttribute("aut_submitted") == null ? "false" : (String)request.getAttribute("aut_submitted")%>'/>
            ${sessionScope.messages.getString("login.login")}: <input type="text" name="login">
            <br/>
            ${sessionScope.messages.getString("login.pass")}: <input type="text" name="pass" />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>