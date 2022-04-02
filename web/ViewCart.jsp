<%-- 
    Document   : ViewCart
    Created on : Mar 15, 2022, 6:45:57 PM
    Author     : nthie
--%>

<%@page import="clone.dao.PlantDAO"%>
<%@page import="clone.dto.Plant"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
        <link rel="stylesheet" href="css/cart.css" type="text/css" />
    </head>
    <body>
        <header>
            <c:import url="Header.jsp"/>
        </header>

        <% 
                int total = 0;
                HashMap<String, Integer> cart = (HashMap)session.getAttribute("cart");
                if (cart != null && !cart.isEmpty()) {
                    Set<String> pids = cart.keySet();
                %>
                <table width="100%">
                <tr><th>Product ID</th><th>Image</th><th>Price</th><th>Quantity</th><th>Total</th><th>Action</th></tr>
                <%
                        for (String pid : pids) {
                            int quantity = cart.get(pid);
                            Plant plant = PlantDAO.getPlant(Integer.parseInt(pid));
                %>
                <form action="mainController" method="post">
                    <tr>
                        <td><a href="mainController?action=detailProduct&pid=<%= pid%>"><%= pid%></a></td>
                        <td><img src="<%= plant.getImgpath()%>" height="60" width="60"></td>
                        <td>$<%= plant.getPrice()%></td>
                        <td><input type="number" value="<%= quantity%>" name="quantity" min="1" max="20"></td>
                        <td class="total">$<%= plant.getPrice()*cart.get(pid)%></td>
                        <td><input type="hidden" name="pid" value="<%= pid%>">
                            <button value="updatecart" name="action">Update</button>
                            <button value="deletecart" name="action">Delete</button></td>
                    </tr>
                </form>
                <%
                    total = total + plant.getPrice()*cart.get(pid);
                        }
                %>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><p style="color: red; font-weight: bold">TOTAL</p></td>
                        <td><p style="color: red; font-weight: bold">$<%= total %></p></td>
                    <form action="mainController" method="post">
                        <td><button value="saveOrder" name="action" id="save">Save order</button></td>
                    </form>
                    </tr>
            </table>
                <%
                    } else {
                %>
                <p>Your cart is empty</p>
                <%
                    }
                %>
        <footer>
            <c:import url="Footer.jsp"/>
        </footer>
    </body>
</html>
