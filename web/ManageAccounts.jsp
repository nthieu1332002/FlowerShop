<%-- 
    Document   : ManageAccounts
    Created on : Mar 17, 2022, 7:17:54 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Accounts</title>
        <link rel="stylesheet" href="css/personal.css" type="text/css" />
    </head>
    <body>
        <c:import url="Header_Admin.jsp"/>
        <form action="mainController" method="post" class="formsearch">
            <input type="hidden" name="index" value="1">
            <input type="text" name="txtSearch" value="<%= (request.getParameter("txtSearch")==null)?"": request.getParameter("txtSearch")%>">
            <button value="manageAccounts" name="action">Search</button>
        </form>
        <table width="100%" class="order">
            <tr><th>ID</th>
                <th>Email</th>
                <th>Full name</th>
                <th>Status</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            <c:forEach var="acc" items="${requestScope.accountList}">
            <tr>
                <td><c:out value="${acc.getAccid()}"></c:out></td>
                <td><c:out value="${acc.getEmail()}"></c:out></td>
                <td><c:out value="${acc.getFullname()}"></c:out></td>
                <td>
                <c:choose>
                    <c:when test="${acc.getStatus() eq 1}"><p style="color:mediumseagreen">Active</p></c:when>
                    <c:otherwise><p style="color:red">Inactive</p></c:otherwise>
                </c:choose>
                </td>
                <td><c:out value="${acc.getPhone()}"></c:out></td>
                <td>
                <c:choose>
                    <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                    <c:otherwise>User</c:otherwise>
                </c:choose>
                </td>
                <td><c:if test="${acc.getRole() eq 0}">
                    <c:url var="mylink" value="mainController">
                        <c:param name="index" value="${param['index']}"></c:param>
                        <c:param name="txtSearch" value="${param['txtSearch']}"></c:param>
                        <c:param name="email" value="${acc.getEmail()}"></c:param>
                        <c:param name="status" value="${acc.getStatus()}"></c:param>
                        <c:param name="action" value="manageAccounts"></c:param>
                    </c:url>
                    <a href="${mylink}" class="link1">Block/Unblock</a>
                </c:if></td>
            </tr>
            </c:forEach>
        </table>
        <div class="pagination">
            <c:forEach begin="1" end="${endP}" var="i">
                <c:url var="mylink1" value="mainController">
                    <c:param name="index" value="${i}"></c:param>
                    <c:param name="txtSearch" value="${param['txtSearch']}"></c:param>
                    <c:param name="action" value="manageAccounts"></c:param>
                </c:url>
                <a href="${mylink1}" class="page"><c:out value="${i}"></c:out></a>
            </c:forEach>
        </div>
    </body>
</html>
