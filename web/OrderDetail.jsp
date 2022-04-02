<%-- 
    Document   : OrderDetail
    Created on : Mar 18, 2022, 3:56:01 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="css/personal.css" type="text/css"/>
    </head>
    <body>
        <header>
            <c:import url="Header_User.jsp"/>
        </header>
            <table class="order" width="100%">
                <tr><th>Order ID</th><th>Plant ID</th><th>Plant Name</th><th>Image</th><th>Quantity</th></tr>
                <c:forEach var="detail" items="${requestScope.detailList}">
                <tr><td>${detail.getOrderid()}</td>
                    <td>${detail.getPlantid()}</td>
                    <td>${detail.getPlantname()}</td>
                    <td><img src='${detail.getImgpath()}' width="150" height="150" class='plantimg'> <br/>
                        $${detail.getPrice()}</td>
                    <td>${detail.getQuantity()}</td>
                </tr>
                </c:forEach>
            </table>
        <footer>
            <c:import url="Footer.jsp"/>
        </footer>
    </body>
</html>
