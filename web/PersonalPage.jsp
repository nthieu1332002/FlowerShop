<%-- 
    Document   : PersonalPage
    Created on : Mar 17, 2022, 2:52:32 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Page</title>
        <link rel="stylesheet" href="css/personal.css" type="text/css" />
    </head>
    <body>
        <header>
            <c:import url="Header_User.jsp"/>
        </header>
        <form action="mainController" method="post" class="formsearch">
            <div>
                From <input type="date" name ="from" value="<%= (request.getParameter("from") == null) ? "" : request.getParameter("from")%>">
                to <input type="date" name="to" value="<%= (request.getParameter("to") == null) ? "" : request.getParameter("to")%>">
                <button value="managePersonal" name="action">Search</button>
            </div>
        </form>
        <div class="pagination">
            <c:forEach begin="1" end="${endP}" var="i">
                <c:url var="mylink1" value="mainController">
                    <c:param name="index" value="${i}"></c:param>
                    <c:param name="from" value="${param['from']}"></c:param>
                    <c:param name="to" value="${param['to']}"></c:param>
                    <c:param name="action" value="managePersonal"></c:param>
                </c:url>
                <a href="${mylink1}" class="page"><c:out value="${i}"></c:out></a>
            </c:forEach>
        </div>
        <table width="100%">
            <tr><th>Order ID</th><th>Order Date</th><th>Ship Date</th><th>Order's status</th><th>Action</th></tr>
        <c:forEach var="ord" items="${requestScope.orderList}">
            <tr><td>${ord.getOrderid()}</td>
            <td>${ord.getOrderdate()}</td>
            <td>${ord.getShipdate()}</td>
            <td><c:choose>
                    <c:when test="${ord.getStatus() eq 1}">
                        <p style="color:orange">Processing</p>
                        <c:url var="mylink" value="mainController">
                            <c:param name="orderid" value="${ord.getOrderid()}"></c:param>
                            <c:param name="action" value="managePersonal"></c:param>
                        </c:url>
                        <a href="${mylink}" class="link2">Cancel</a>
                    </c:when>
                    <c:when test="${ord.getStatus() eq 2}"><p style="color:blue">Completed</p></c:when>
                    <c:otherwise><p style="color:red">Canceled</p></c:otherwise>
                </c:choose></td>
            <td><a href="mainController?action=manageOrderDetail&orderid=${ord.getOrderid()}">Detail</a></td>
            </tr>
        </c:forEach>
        </table>
        
        
        <footer>
            <c:import url="Footer.jsp"/>
        </footer>
    </body>
</html>
