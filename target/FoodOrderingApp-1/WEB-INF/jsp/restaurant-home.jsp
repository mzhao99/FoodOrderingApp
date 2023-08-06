<%-- 
    Document   : restaurant-home
    Created on : Jun 28, 2023, 2:39:57 PM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurant Admin Panel</title>
    </head>
    <body>
        <h1>Welcome, ${restaurantName}</h1>

        <form action="view-menu-res.htm" method="post">
            <input type="hidden" name="resId" value=${restaurantId} />
            <input type="submit" name="viewMenu" value="View Current Menu" />
        </form>
        <!--<a href="view-menu-res.htm?resId=${restaurantId}">View Current Menu</a>-->
        <br />
        <form action="view-order-res.htm" method="post">
            <input type="hidden" id="resId" name="resId" value=${restaurantId} />
            <input type="submit" name="viewOrder" value="View All Orders" />
        </form>
        <!--<a href="view-order-res.htm">View All Orders</a>-->
        <hr/>
        <form action="login.htm" method="get">
            <input type="submit" name="logout" value="Logout" />
        </form>
    </body>
</html>
