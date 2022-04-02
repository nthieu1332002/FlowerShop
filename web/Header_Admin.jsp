<%-- 
    Document   : header_loginedAdmin
    Created on : Mar 17, 2022, 7:20:56 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/index.css" type="text/css"/> 
    </head>
    <body>
        <% 
        String name = (String)session.getAttribute("name");
        Integer role = (Integer)session.getAttribute("role");
        if (name == null || role == 0) {
        %>
        <script>
            alert('You must be admin to view this!');
            location="mainController?action=logout";
        </script>
        <%
        } else {
            %>
            <header>
                <ul>
                    <li><a href="mainController?action=manageAccounts">Manage Accounts</a></li>
                    <li><a href="mainController?action=manageOrders">Manage Orders</a></li>
                    <li><a href="mainController?action=managePlants">Manage Plants</a></li>
                    <li><a href="mainController?action=manageCategories">Manage Categories</a></li>
                    <li style="float: right;"><a href="mainController?action=logout" class="active">Log out</a></li>
                    <li style="float: right; font-size: 15px; padding: 0 5px;"><p>Hey <%= name%>!</p></li>
                </ul>
            </header>
        <%
        }
        %>


    </body>
</html>