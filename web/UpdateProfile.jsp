<%-- 
    Document   : UpdateAccount
    Created on : Mar 18, 2022, 4:44:34 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Profile</title>
        <link rel="stylesheet" href="css/signin.css" type="text/css" />

    </head>
    <body>
        <%
            String email = (String) session.getAttribute("email");
            String password = (String)session.getAttribute("password");
            System.out.println(password);
            if (email == null) {
        %>
        <p style="text-align: center"><font color='red'>You must login to update your profile</font></br>
        <a href="index.jsp">Return home</a></p>
        
        <%
            } else {
        %>
            <div class="container">
                <div class="login">
                    <h1><span class="h1">Update Information</span></h1>
                    <form action="mainController?email=<%= email%>&password=<%= password%>" method="post" autocomplete="off" class="inputform">
                        <input type="text" name="newname" pattern="(.|\s)*\S(.|\s)*" class="sign-up-input" placeholder="New name">
                        <input type="text" name="newphone" pattern="[0-9]{1,10}" class="sign-up-input" placeholder="New phone">
                        <input type="hidden" name="password" value="<%= request.getAttribute("password")%>">
                        <button value="Update" name="action" class="submit">Update</button>
                    </form>
                    <div class="or"><span> Or </span></div>
                    <div class="return"><a href="mainController?action=managePersonal">Return personal page</a></div>
                </div>
            </div>
        </form>
        <% }%>
    </body>
</html>
