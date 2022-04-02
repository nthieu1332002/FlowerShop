<%-- 
    Document   : ManageCategories
    Created on : Mar 17, 2022, 7:18:39 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Categories</title>
        <link rel="stylesheet" href="css/personal.css" type="text/css" />
    </head>
    <body>
        <c:import url="Header_Admin.jsp"/>
        <table width="50%" style="margin: 0 auto" class="order">
            <tr><th>ID</th>
                <th>Email</th>
            </tr>
            <c:forEach var="cate" items="${requestScope.cateList}">
            <tr>
                <td><c:out value="${cate.getCateid()}"></c:out></td>
                <td><c:out value="${cate.getCatename()}"></c:out></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>