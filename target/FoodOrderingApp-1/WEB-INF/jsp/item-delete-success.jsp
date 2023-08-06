<%-- 
    Document   : item-delete-success
    Created on : Jun 30, 2023, 9:46:09 PM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Updated Successfully</title>
    </head>
    <body>
        <h1>Item Deleted Successfully!</h1>
        <form action="view-menu-res.htm" method="post">
            <input type="hidden" name="resId" value=${resId} />
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
