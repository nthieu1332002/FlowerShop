<%-- 
    Document   : Header_User
    Created on : Mar 17, 2022, 4:14:22 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/personal.css" type="text/css" />
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <script>
            alert('You must login to view personal page!');
            location='SignIn.jsp';
        </script>
        <% } else { %>

        <ul>
            <li><a href="mainController?action=manageIndex" class="active">Home</a></li>
            <li><a href="UpdateProfile.jsp">Change profile</a></li>
            <li><a href="CompletedOrders.jsp">Completed orders</a></li>
            <li><a href="CanceledOrders.jsp">Canceled orders</a></li>
            <li><a href="ProcessingOrders.jsp">Processing orders</a></li>
            <li style="float: right;"><a href="mainController?action=logout" class="active">Log out</a></li>
            <li style="float: right; font-size: 15px; padding: 0 5px; color: white;"><p>Hello, <%= name%>!</p></li>
        </ul>
        <% }%>
    </body>
</html>
