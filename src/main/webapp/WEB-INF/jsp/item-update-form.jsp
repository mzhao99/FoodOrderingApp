<%-- 
    Document   : item-update-form
    Created on : Jun 30, 2023, 10:55:21 PM
    Author     : manlingzhao
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Item</title>
    </head>
    <body>
        <h1>Update Below:</h1>
        <form:form modelAttribute="updateItem" method="post" enctype="multipart/form-data">
            <input type="hidden" name="itemId" value=${itemId} />
            <input type="hidden" name="resId" value=${resId}>
            Name: <form:input path="name" /> <span style="color:red;"><form:errors path="name" /></span><br/>
            Price: $<form:input path="price"/> <span style="color:red;"><form:errors path="price" /></span><br/>
            Photo: <input type="file" name="photo" required="required"/> <span style="color:red;"><form:errors path="photo" /></span><br/>
            <input type="submit" value="Submit"/>
        </form:form>
        <hr/>
        <form action="view-menu-res.htm" method="post">
            <input type="hidden" name="resId" value=${resId} />
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
