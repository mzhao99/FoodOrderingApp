<%-- 
    Document   : cart-add-successful
    Created on : Jul 1, 2023, 2:23:56 AM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${itemName} Added To Cart Successful!</h1>
        <form action="view-cart.htm" method="post">
            <input type="hidden" name="un" value=${un} />
            <input type="submit" name="cart" value="View Cart" />
        </form>
        <hr/>
        <form action="selected-restaurant.htm" method="post">
            <input type="hidden" name="restaurant" value=${resId} />
            <input type="hidden" name="un" value=${un} />
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
