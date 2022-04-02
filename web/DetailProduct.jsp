<%-- 
    Document   : DetailProduct
    Created on : Mar 18, 2022, 3:32:32 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail product</title>
        <link rel="stylesheet" href="css/index.css" type="text/css"/>
    </head>
    <body>
        <header>
            <c:import url="Header.jsp"/>
        </header>

            
            <table class='product2'>

                <tr><td><img src='${plantObj.getImgpath()}' class='plantimg' height="150px" width="150px"></td></tr>
                    <tr><td>Product ID: ${plantObj.getId()}</td></tr>
                    <tr><td>Product name: ${plantObj.getName()}</td></tr>
                    <tr><td>Price: $${plantObj.getPrice()}</td></tr>
                    <tr><td>
                        <c:choose>
                        <c:when test="${plantObj.getStatus() eq 1}"><p style="color: #00cc00">Available</c:when>
                        <c:otherwise><p style="color: red">Out of stock</p></c:otherwise>
                        </c:choose>
                    </td></tr>
                    <tr><td>Category: <c:choose>
                                <c:when test="${plantObj.getCateid() eq 1}">Hoa hong</c:when>
                                <c:when test="${plantObj.getCateid() eq 2}">Hoa cuc</c:when>
                                <c:otherwise>Khac</c:otherwise>
                            </c:choose></td></tr>
            </table>

        <footer>
            <c:import url="Footer.jsp"/>
        </footer>
    </body>
</html>
