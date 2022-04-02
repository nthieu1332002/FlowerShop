<%-- 
    Document   : Register
    Created on : Mar 15, 2022, 4:43:42 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="css/signup.css">
        
    </head>
    <body>
        <div class="container">
            <div class="signup">
                <h1><span class="h1">Create Account</span></h1>
                <form action="mainController" method="post" class="inputform">
                    <input type="email" name="email" required autocomplete="off" class="sign-up-input" placeholder="Email">
                    <input type="text" name="fullname" required pattern="(.|\s)*\S(.|\s)*" title="Cannot be blank!" class="sign-up-input" placeholder="Full name">
                    <input type="password" name="password" required pattern="[A-Za-z0-9]+" title="Do not contain blank!" autocomplete="off" class="sign-up-input" placeholder="Password">
                    <input type="text" name="phone" required pattern="[0-9]{1,10}" title="Phone is maximum 10 numbers" autocomplete="off" class="sign-up-input" placeholder="Phone"/>
                    <button value="register" name="action" class="submit">CREATE</button>
                </form>
                <span class="signin"><p>Already have an account?<a href="SignIn.jsp"> Login here</a></p></span>
                <div class="or"><span> Or </span></div>
                <div class="return"><a href="mainController?action=manageIndex">Return home</a></div>
            </div>
        </div>
    </body>
</html>
