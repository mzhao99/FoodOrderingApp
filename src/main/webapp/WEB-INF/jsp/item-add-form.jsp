<%-- 
    Document   : item-form
    Created on : Jun 26, 2023, 4:29:41 PM
    Author     : manlingzhao
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Item</title>
    </head>
    <body>
        <h1>Add a new item</h1>

        <form:form modelAttribute="items" method="post" enctype="multipart/form-data">
            Item Name: <form:input path="name"/> <span style="color:red;"><form:errors path="name"/></span><br/>
            Item Price: $<form:input path="price"/> <span style="color:red;"><form:errors path="price"/></span><br/>
            Upload photo: <input type="file" name="photo" required="required"/><form:errors path="photo"/><br/>
            <input type="submit" value="Submit"/>
        </form:form>
        <hr/>
        <form action="view-menu-res.htm" method="post">
            <input type="hidden" name="resId" value=${resId} />
            <input type="submit" name="back" value="Back" />
        </form>    
    </body>
</html>
