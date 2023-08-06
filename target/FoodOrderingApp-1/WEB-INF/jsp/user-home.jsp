<%-- 
    Document   : user-home
    Created on : Jun 28, 2023, 5:39:18 PM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Home</title>
    </head>
    <body>
        <h1>Welcome ${firstName} ${lastName}!</h1>
        <h3>Choose a restaurant:</h3>
        <form action="selected-restaurant.htm" method="post">
            <input type="hidden" name="un" value=${un}>
            <c:forEach var="value" items="${resList}">
                <input type="radio" name="restaurant" value="${value.getId()}" required>
                <b><c:out value="${value.getName()}"/> - </b>
                <em><c:out value="${value.getCuisine()}"/></em><br/>
            </c:forEach>
            <br/>
            <input type="submit" value="Next"/>
        </form>
        <hr/>
        <form action="view-cart.htm" method="post">
            <input type="hidden" name="un" value=${un}>
            <input type="submit" name="view-cart" value="View Cart" />
        </form>
        <br/>
        <form action="view-order-user.htm" method="post">
            <input type="hidden" name="un" value=${un}>
            <input type="submit" name="view-order" value="View My Orders" />
        </form>
        <hr/>
        <form action="login.htm" method="get">
            <input type="submit" name="logout" value="Logout" />
        </form>
    </body>
</html>
