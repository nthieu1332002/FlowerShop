<%-- 
    Document   : ManagePlants
    Created on : Mar 17, 2022, 7:20:00 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Plants</title>
        <link rel="stylesheet" href="css/personal.css" type="text/css"/>
        <script src="https://kit.fontawesome.com/fc43bf7534.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <c:import url="Header_Admin.jsp"/>
        <form action="mainController" method="post" class="formsearch">
            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch")==null)?"": request.getParameter("txtsearch")%>">
            <button value="managePlants" name="action">Search</button>
        </form>
            <a href="AddNewPlant.jsp" class="add"><i class="fa fa-solid fa-plus"></i> Add a new Plant</a>
        <table width="100%" class="order">
            <tr><th>PID</th>
                <th>Plant name</th>
                <th>Price</th>
                <th>Image</th>
                <th>Status</th>
                <th>Cate ID</th>
                <th>Cate Name</th>
                <th>Action</th>
            </tr>
            <c:forEach var="plant" items="${requestScope.plantList}">
            <tr>
                <td><c:out value="${plant.getId()}"></c:out></td>
                <td><c:out value="${plant.getName()}"></c:out></td>
                <td><c:out value="${plant.getPrice()}"></c:out></td>
                <td><img src="<c:out value="${plant.getImgpath()}"></c:out>" height="100" width="100"></td>
                <td>
                <c:choose>
                    <c:when test="${plant.getStatus() eq 1}"><p style="color: #00cc00">Available</c:when>
                    <c:otherwise><p style="color: red">Out of stock</p></c:otherwise>
                </c:choose>
                </td>
                <td><c:out value="${plant.getCateid()}"></c:out></td>
                <td>
                <c:choose>
                    <c:when test="${plant.getCateid() eq 1}">Hoa hong</c:when>
                    <c:when test="${plant.getCateid() eq 2}">Hoa cuc</c:when>
                    <c:otherwise>Khac</c:otherwise>
                </c:choose>
                </td>
                <td><c:url var="mylink" value="mainController">
                        <c:param name="index" value="${param['index']}"></c:param>
                        <c:param name="txtsearch" value="${param['txtSearch']}"></c:param>
                        <c:param name="PID" value="${plant.getId()}"></c:param>
                        <c:param name="status" value="${plant.getStatus()}"></c:param>
                        <c:param name="action" value="managePlants"></c:param>
                    </c:url>
                    <a href="${mylink}" class="link1">Change status</a>
                </td>
            </tr>
            </c:forEach>
        </table>
        <div class="pagination">
            <c:forEach begin="1" end="${endP}" var="i">
                <c:url var="mylink2" value="mainController">
                    <c:param name="index" value="${i}"></c:param>
                    <c:param name="txtsearch" value="${param['txtsearch']}"></c:param>
                    <c:param name="status" value="${param['status']}"></c:param>
                    <c:param name="action" value="managePlants"></c:param>
                </c:url>
                <a href="${mylink2}" class="page"><c:out value="${i}"></c:out></a>
            </c:forEach>
        </div>
    </body>
</html>