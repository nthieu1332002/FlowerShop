<%-- 
    Document   : Header
    Created on : Mar 15, 2022, 4:56:24 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/index.css" type="text/css"/>
    </head>
    <body>
        
        <header>
            <nav>
                <ul>
                    <li><a href="mainController?action=manageIndex" class="active">Home</a></li>
                    <li><a href="Register.jsp">Register</a></li>
                    <li><a href="SignIn.jsp" >Login</a></li>
                    <li><a href="mainController?action=managePersonal">Profile</a></li>
                    <li><a href="ViewCart.jsp">View cart</a></li>
                    <li style="float: right;"><a href="mainController?action=logout" class="active">Log out</a></li>
                </ul>
            </nav>
        </header>
    </body>
</html>
