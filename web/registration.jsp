<%-- 
    Document   : registration
    Created on : Jan 6, 2014, 3:27:52 PM
    Author     : koxa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.messages.getString("registration.title")}</title>
    </head>
    <body>
        <form action='./RegisterController.do' method='post'>
            <span>${sessionScope.messages.getString("registration.required")}</span>
            <span style="color:red"><%=request.getAttribute("req_exception") == null ? "" : "<br>" + (String)request.getAttribute("req_exception")%></span>
            <fieldset>
                <legend>${sessionScope.messages.getString("registration.legend")}</legend>
                <input type='hidden' name='submitted' value='<%=request.getAttribute("req_submitted") == null ? "false" : (String)request.getAttribute("req_submitted")%>'/>
                <label>${sessionScope.messages.getString("registration.nickname")} </label>
                <input type='text' name='nick' maxlength="20" value="<%=request.getAttribute("req_nick") == null ? "" : (String)request.getAttribute("req_nick")%>"/>
                <br>
                <label>${sessionScope.messages.getString("registration.password")} </label>
                <input type='password' name='password' maxlength="64" value="<%=request.getAttribute("req_password") == null ? "" : request.getAttribute("req_password")%>"/>
                <br>
                <label>${sessionScope.messages.getString("registration.first.name")} </label>
                <input type='text' name='first_name' maxlength="32" value="<%=request.getAttribute("req_first_name") == null ? "" : request.getAttribute("req_first_name")%>"/>
                <br>
                <label>${sessionScope.messages.getString("registration.last.name")} </label>
                <input type='text' name='second_name' maxlength="32" value="<%=request.getAttribute("req_second_name") == null ? "" : request.getAttribute("req_second_name")%>"/>
                <br>
                <label>${sessionScope.messages.getString("registration.address")} </label>
                <input type='text' name='address' maxlength="64" value="<%=request.getAttribute("req_address") == null ? "" : request.getAttribute("req_address")%>"/>
                <br>
                <label>${sessionScope.messages.getString("registration.email")} </label>
                <input type='text' name='email' maxlength="32" value="<%=request.getAttribute("req_email") == null ? "" : request.getAttribute("req_email")%>"/>
                <br>                
                <input type='submit' name='Submit' value='Submit'/>
            </fieldset>
        </form>
    </body>
</html>
