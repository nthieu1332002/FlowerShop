<%-- 
    Document   : CompletedOrders
    Created on : Mar 18, 2022, 5:35:18 PM
    Author     : nthie
--%>

<%@page import="clone.dto.Order"%>
<%@page import="clone.dao.OrderDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Completed Orders</title>
        <link rel="stylesheet" href="css/personal.css" type="text/css" />
    </head>
    <body>
        <%
            String email = (String) session.getAttribute("email");
        %>
        <header>
            <%@include file="Header_User.jsp" %>
        </header>
       
        <%
            ArrayList<Order> list = OrderDAO.getOrders(email);
            String[] status = {"", "processing", "completed", "canceled"};
            if (list != null && !list.isEmpty()) { %>
            <table width="100%">
                <tr><th>Order ID</th><th>Order Date</th><th>Ship Date</th><th>Order's status</th><th>Action</th></tr>    
                <%
                    for (Order ord : list) {
                        if (ord.getStatus() == 2) {
                %>

                <tr><td><%= ord.getOrderid()%></td>
                    <td><%= ord.getOrderdate()%></td>
                    <td><%= ord.getShipdate()%></td>
                    <td><%= status[ord.getStatus()]%></td>
                    <td><a href="mainController?action=manageOrderDetail&orderid=<%= ord.getOrderid()%>">Detail</a></td>
                </tr>

                <%
                    }
                } %>
            </table>
        <%
            }
        %>
        <footer>
            <%@include file="Footer.jsp" %>
        </footer>
    </body>
</html>