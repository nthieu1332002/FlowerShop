<%-- 
    Document   : Index
    Created on : Mar 15, 2022, 4:54:51 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link rel="stylesheet" href="css/index.css" type="text/css"/>
    </head>
    <body>
        <header>
            <c:import url="Header.jsp"/>
        </header>
        <form action="mainController" method="post" class="formsearch">
            <div>
            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null) ? "" : request.getParameter("txtsearch")%>">
            <select name="searchby">
                <option value="byname">By name</option>
                <option value="bycate">By category</option>
            </select>
            <button value="manageIndex" name="action">Search</button>
            </div>
        </form>
            
        <div class="pagination">
            <c:forEach begin="1" end="${endP}" var="i">
                <c:url var="mylink1" value="mainController">
                    <c:param name="index" value="${i}"></c:param>
                    <c:param name="txtsearch" value="${param['txtsearch']}"></c:param>
                    <c:param name="searchby" value="${param['searchby']}"></c:param>
                    <c:param name="action" value="manageIndex"></c:param>
                </c:url>
                <a href="${mylink1}" class="page"><c:out value="${i}"></c:out></a>
            </c:forEach>    
        </div>
        <div class="container">
            <p></p>
            <c:forEach var="p" items="${requestScope.indexList}">
            <table class='product'>
                <tr><td><img src='${p.getImgpath()}' class='plantimg' height="150px" width="150px"></td></tr>
                    <tr><td>Product ID: ${p.getId()}</td></tr>
                    <tr><td>Product name: ${p.getName()}</td></tr>
                    <tr><td>Price: $${p.getPrice()}</td></tr>
                    <tr><td>
                        <c:choose>
                        <c:when test="${p.getStatus() eq 1}"><p style="color: #00cc00">Available</c:when>
                        <c:otherwise><p style="color: red">Out of stock</p></c:otherwise>
                        </c:choose>
                    </td></tr>
                    <tr><td>Category: <c:choose>
                                <c:when test="${p.getCateid() eq 1}">Hoa hong</c:when>
                                <c:when test="${p.getCateid() eq 2}">Hoa cuc</c:when>
                                <c:otherwise>Khac</c:otherwise>
                            </c:choose></td></tr>
                <form action="mainController" method="post">
                    <tr><td><input type="hidden" name="pid" value="${p.getId()}">
                            <button name="action" value="addtocart">Add to cart</button></td></tr>
                </form>
            </table>
            </c:forEach>
        </div>

        
        
        <footer>
            <c:import url="Footer.jsp"/>
        </footer>

    </body>
</html>
