<%-- 
    Document   : login
    Created on : Jun 25, 2023, 7:13:08 PM
    Author     : manlingzhao
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Welcome to the Food Ordering App!</h1>
        <h2>Please Login:</h2>
        <form:form modelAttribute="login" method="post">
            Username: <form:input type="text" path="username" /> <span style="color:red;"><form:errors path="username" /></span><br/>
            Password: <form:input type="password" path="password" /> <span style="color:red;"><form:errors path="password" /></span><br/>
            Are you logging in as a restaurant owner?
            <input type="radio" name="user-type" value="owner" required/>Yes
            <input type="radio" name="user-type" value="non-owner"/>No
            <br />
            <input type="submit" name="login" value="Login" />
        </form:form>
        <hr/>
        <a href="user-create.htm">User Sign Up</a><br />
        <a href="restaurant-create.htm">Restaurant Sign Up</a>
    </body>
</html>
