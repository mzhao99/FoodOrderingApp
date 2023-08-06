<%-- 
    Document   : login-restaurant-create
    Created on : Jun 28, 2023, 7:38:26 PM
    Author     : manlingzhao
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurant Creation</title>
    </head>
    <body>
        <h1>Create Restaurant Account</h1>
        <form:form modelAttribute="restaurantCreate" method="post">
            Username: <form:input type="text" path="username" /> <span style="color:red;"><form:errors path="username" /></span><br/>
            Password: <form:input type="password" path="password" /> <span style="color:red;"><form:errors path="password" /></span><br/>
            Restaurant Name: <form:input type="text" path="name" /> <span style="color:red;"><form:errors path="name" /></span><br/>
            Cuisine Type: <form:input type="text" path="cuisine" /> <span style="color:red;"><form:errors path="cuisine" /></span><br/>
            Phone: <form:input type="text" path="phone" /> <span style="color:red;"><form:errors path="phone" /></span><br/>
            Address: <form:input type="text" path="address" /> <span style="color:red;"><form:errors path="address" /></span><br/><br/>
            <input type="submit" name="create-restaurant" value="Submit" />
        </form:form>
        <hr/>
        <form action="login.htm" method="get">
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
