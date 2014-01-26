<%-- 
    Document   : tasks_list
    Created on : Jan 9, 2014, 5:06:10 PM
    Author     : koxa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.messages.getString("tasks_list.title")}</title>
    </head>
    <body>
        <table bordercolor="black" border="2" width="100%">
            <tr>
                <td>
                    ${sessionScope.messages.getString("tasks_list.isbn")}
                </td>
                <td>
                    ${sessionScope.messages.getString("tasks_list.user")}
                </td>
                <td>
                    ${sessionScope.messages.getString("tasks_list.action")}
                </td>
            </tr>
            <c:forEach var="ordered_loan" items="${requestScope.ordered_loans}">
                <tr>
                    <td>
                        ${ordered_loan.getBook().getIsbn()}
                    </td>
                    <td>
                        ${ordered_loan.getCard().getUser().getFirstName()}   ${ordered_loan.getCard().getUser().getSecondName()}
                    </td>
                    <td>
                        <a href="./TaskController.do?command=proceedOrderedLoan&isbn=${ordered_loan.getBook().getIsbn()}&card=${ordered_loan.getCard().getId()}">${sessionScope.messages.getString("tasks_list.manage")}</a>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach var="taken_loan" items="${requestScope.taken_loans}">
                <tr>
                    <td>
                        ${taken_loan.getBook().getIsbn()}
                    </td>
                    <td>
                        ${taken_loan.getCard().getUser().getFirstName()} ${taken_loan.getCard().getUser().getSecondName()}
                    </td>
                    <td>
                        <a href="./TaskController.do?command=completeOrderedLoan&isbn=${taken_loan.getBook().getIsbn()}&card=${taken_loan.getCard().getId()}">${sessionScope.messages.getString("tasks_list.manage")}</a>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach var="ordered_reading_room" items="${requestScope.ordered_reading_rooms}">
                <tr>
                    <td>
                        ${ordered_reading_room.getBook().getIsbn()}
                    </td>
                    <td>
                        ${ordered_reading_room.getCard().getUser().getFirstName()} ${ordered_reading_room.getCard().getUser().getSecondName()}
                    </td>
                    <td>
                        <a href="./TaskController.do?command=proceedOrderedReadingRoom&isbn=${ordered_reading_room.getBook().getIsbn()}&card=${ordered_reading_room.getCard().getId()}">${sessionScope.messages.getString("tasks_list.manage")}</a>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach var="taken_reading_room" items="${requestScope.taken_reading_rooms}">
                <tr>
                    <td>
                        ${taken_reading_room.getBook().getIsbn()}
                    </td>
                    <td>
                        ${taken_reading_room.getCard().getUser().getFirstName()} ${taken_reading_room.getCard().getUser().getSecondName()}
                    </td>
                    <td>
                        <a href="./TaskController.do?command=completeOrderedReadingRoom&isbn=${taken_reading_room.getBook().getIsbn()}&card=${taken_reading_room.getCard().getId()}">${sessionScope.messages.getString("tasks_list.manage")}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
