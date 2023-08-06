<%-- 
    Document   : item-update-success
    Created on : Jun 30, 2023, 11:19:29 PM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item Updated Successful</title>
    </head>
    <body>
        <h1>Item Updated Successful!</h1>
        <form action="view-menu-res.htm" method="post">
            <input type="hidden" name="resId" value=${resId} />
            <input type="submit" name="viewMenu" value="Back to Menu" />
        </form>
    </body>
</html>
