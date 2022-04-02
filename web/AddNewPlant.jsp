<%-- 
    Document   : AddNewPlant
    Created on : Mar 17, 2022, 7:06:14 PM
    Author     : nthie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new plant</title>
        <link rel="stylesheet" href="css/personal.css" type="text/css"/>
    </head>
    <body>
        <c:import url="Header_Admin.jsp"/>
        <table width="100%" class="order">
            <tr>
                <th>Plant name</th>
                <th>Price</th>
                <th>Image</th>
                <th>Description</th>
                <th>Status</th>
                <th>Cate ID</th>
            </tr>
        <form action="mainController" method="post">
            <tr><td><input type="text" name="name" required></td>
                <td><input type="text" name="price" placeholder="$" required>$</td>
                <td><input type="text" name="image" placeholder="images/....jpg" required></td>
                <td><input type="text" name="description" required></td>
                <td><select name="status">
                        <option value="1">Available</option>
                        <option value="0">Unavailable</option>
                    </select>
                <td><select name="cateid">
                        <option value="1">Hoa hong</option>
                        <option value="2">Hoa cuc</option>
                        <option value="3">Khac</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td><button type="submit" name="action" value="addnewplant" class="save">Save</button>
                <td></td>
                <td></td>
            </tr>
        </form>
        </table>
    </body>
</html>
