<%-- 
    Document   : ManageOrders
    Created on : Mar 17, 2022, 7:19:32 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Orders</title>
        <link rel="stylesheet" href="css/personal.css" type="text/css" />
    </head>
    <body>
        <c:import url="Header_Admin.jsp"/>
        <form action="mainController" method="post" class="formsearch">
            <select name="sortby">
                <option value="orderid" <%if((request.getParameter("sortby") != null) && request.getParameter("sortby").equals("orderid")){ %> selected <%} %>>Order ID</option>
                <option value="orderdate" <%if((request.getParameter("sortby") != null) && request.getParameter("sortby").equals("orderdate")){ %> selected <%} %>>Order Date</option>
            </select>
            <select name="order">
                <option value="asc" <%if((request.getParameter("order") != null) && request.getParameter("order").equals("asc")){ %> selected <%} %>>ASC</option>
                <option value="desc" <%if((request.getParameter("order") != null) && request.getParameter("order").equals("desc")){ %> selected <%} %>>DESC</option>
            </select>
            <button value="manageOrders" name="action">Sort</button>
        </form>
        <table width="100%" class="order">
            <tr><th>Order ID</th>
                <th>Order Date</th>
                <th>Ship Date</th>
                <th>Status</th>
                <th>Account ID</th>
                <th>Detail</th>
                <th>Action</th>
            </tr>
            <c:forEach var="ord" items="${requestScope.orderList}">
            <tr>
                <td><c:out value="${ord.getOrderid()}"></c:out></td>
                <td><c:out value="${ord.getOrderdate()}"></c:out></td>
                <td><c:out value="${ord.getShipdate()}"></c:out></td>
                <td>
                <c:choose>
                    <c:when test="${ord.getStatus() eq 1}"><p style="color: orange;">Processing</p></c:when>
                    <c:when test="${ord.getStatus() eq 2}"><p style="color: blue;">Completed</p></c:when>
                    <c:otherwise><p style="color: red;">Canceled</p></c:otherwise>
                </c:choose>
                </td>
                <td><c:out value="${ord.getAccid()}"></c:out></td>
                <td><a href="mainController?action=manageOrderDetail&orderid=${ord.getOrderid()}">Detail</a></td>
                <td><c:if test="${ord.getStatus() eq 1}">
                        <c:url var="mylink" value="mainController">
                            <c:param name="orderid" value="${ord.getOrderid()}"></c:param>
                            <c:param name="index" value="${param['index']}"></c:param>
                            <c:param name="sortby" value="${param['sortby']}"></c:param>
                            <c:param name="order" value="${param['order']}"></c:param>
                            <c:param name="do" value="approve"></c:param>
                            <c:param name="action" value="manageOrders"></c:param>
                        </c:url>
                        <a href="${mylink}" class="link1">Approve</a>
                        <c:url var="mylink1" value="mainController">
                            <c:param name="orderid" value="${ord.getOrderid()}"></c:param>
                            <c:param name="index" value="${param['index']}"></c:param>
                            <c:param name="sortby" value="${param['sortby']}"></c:param>
                            <c:param name="order" value="${param['order']}"></c:param>
                            <c:param name="do" value="disapprove"></c:param>
                            <c:param name="action" value="manageOrders"></c:param>
                        </c:url>
                        <a href="${mylink1}" class="link2">Disapprove</a>
                </c:if></td>
            </tr>
            </c:forEach>
        </table>
        <div class="pagination">
            <c:forEach begin="1" end="${endP}" var="i">
                <c:url var="mylink2" value="mainController">
                    <c:param name="index" value="${i}"></c:param>
                    <c:param name="sortby" value="${param['sortby']}"></c:param>
                    <c:param name="order" value="${param['order']}"></c:param>
                    <c:param name="action" value="manageOrders"></c:param>
                </c:url>
                <a href="${mylink2}" class="page"><c:out value="${i}"></c:out></a>
            </c:forEach>
        </div>
    </body>
</html>
