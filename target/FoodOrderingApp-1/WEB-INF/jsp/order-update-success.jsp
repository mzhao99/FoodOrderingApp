<%-- 
    Document   : order-update-success
    Created on : Jul 2, 2023, 3:12:18 PM
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
        <h1>Status Updated Successful</h1>
        <form action="view-order-res.htm" method="post">
            <input type="hidden" name="resId" value=${resId}>
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
