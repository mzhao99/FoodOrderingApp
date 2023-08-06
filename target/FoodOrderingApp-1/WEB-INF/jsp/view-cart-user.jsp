<%-- 
    Document   : view-cart-user
    Created on : Jul 1, 2023, 12:53:29 PM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
    </head>
    <body>
        <h1>My Cart</h1>
        <form action="cart-action.htm" method="post">
            <input type="hidden" name="un" value=${username}>
            <c:forEach var="map" items="${map}">
                <b><c:out value="${map.key}"/></b><br>
                <c:forEach var="cart" items="${map.value}">
                    <input type="checkbox" name="single-item" value="${cart.getId()}">
                    <c:out value="${cart.getItemName()} - "/>
                    <c:out value="$${cart.getItemPrice()}"/><br>
                </c:forEach>
                <hr>
            </c:forEach>
            <b>Total: </b><c:out value="$${total}"/><hr>
            <input type="submit" name="delete-item" value="Remove Item"/>
            <input type="submit" name="checkout" value="Checkout"/>
        </form>
        <br>    
        <form action="login.htm" method="post">
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="password" value=${password} />
            <input type="hidden" name="user-type" value=${usertype} />
            <input type="submit" name="back" value="Back to Home" />
        </form>
    </body>
</html>
