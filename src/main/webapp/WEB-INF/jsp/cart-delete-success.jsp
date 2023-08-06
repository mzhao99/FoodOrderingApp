<%-- 
    Document   : cart-delete-successful
    Created on : Jul 1, 2023, 4:26:48 PM
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
        <h1>Item(s) Removed From Cart Successfully!</h1>
        <form action="view-cart.htm" method="post">
            <input type="hidden" name="un" value=${un} />
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
