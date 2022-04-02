<%-- 
    Document   : SignIn
    Created on : Mar 17, 2022, 1:30:29 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign in</title>
        <link rel="stylesheet" type="text/css" href="css/signin.css"/>
    </head>
    <body>
        <div class="container">
            <div class="login">
                <h1><span class="h1">Member Login</span></h1>
                <form action="mainController" method="post" class="inputform">
                    <input type="email" name="txtemail" class="sign-up-input" placeholder="Email" autocomplete="off">
                    <input type="password" name="txtpassword" class="sign-up-input" placeholder="Password" autocomplete="off">
                    <input type="checkbox" name="savelogin" value="savelogin" id="savelogin" checked='true' style="margin-left: 20px;">
                    <label id="savelogin" for="savelogin">Remember me</label>
                    <button value="login" name="action" class="submit">SIGN IN</button>
                </form>
                <div class="forget"><a href="#"><span>Forgot your password?</span></a></div>
                <span class="signup">
                    <p>Do not have an account yet?<a href="Register.jsp"> Sign up here</a></p>
                </span>
                <div class="or"><span> Or </span></div>
                <div class="return"><a href="mainController?action=manageIndex">Return home</a></div>
            </div>
        </div>
        
    </body>
</html>
