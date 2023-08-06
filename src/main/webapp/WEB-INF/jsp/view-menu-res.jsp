<%-- 
    Document   : view-menu-res
    Created on : Jun 29, 2023, 12:57:50 PM
    Author     : manlingzhao
--%>
<%@page import="info6250.foodorderingapp.pojo.Item"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurant Panel Menu</title>
    </head>
    <body>
        <h1>All Items</h1>
        <form action="item-update-res.htm" method="get">
            <input type="hidden" name="resId" value=${resId}>
            <table>
                <c:forEach var="value" items="${itemList}">
                    <tr>
                    <td><input type="radio" name="item" value="${value.getId()}" required></td>
                    <td><c:out value="${value.getName()}"/></td>
                    <td><c:out value="$${value.getPrice()}"/></td>
                    <td><img src="<c:url value="/assets/${value.getPhotoFilePath()}"/>" width="140" height="100"/></td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <input type="submit" name="updateItem" value="Update Item" /><br/><br/> 
            <input type="submit" name="deleteItem" value="Delete Item" />
        </form>
        <br/>    
        <form action="item-add.htm" method="get">
            <input type="hidden" name="resId" value=${resId}>
            <input type="submit" name="addItem" value="Add new item" />
        </form>
        <hr/>
        
        <form action="login.htm" method="post">
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="password" value=${password} />
            <input type="hidden" name="user-type" value=${usertype} />
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
