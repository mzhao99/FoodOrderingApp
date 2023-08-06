<%-- 
    Document   : item-success
    Created on : Jun 26, 2023, 4:29:59 PM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item added successful</title>
    </head>
    <body>
        <h1>Item added successful!</h1>
        <form action="view-menu-res.htm" method="post">
            <input type="hidden" name="resId" value=${resId} />
            <input type="submit" name="viewMenu" value="Back to Menu" />
        </form>
    </body>
</html>
