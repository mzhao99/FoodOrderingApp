<%-- 
    Document   : login-create
    Created on : Jun 28, 2023, 6:40:10 PM
    Author     : manlingzhao
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Sign Up</title>
    </head>
    <body>
        <h1>User Sign Up</h1>
        <form:form modelAttribute="userCreate" method="post">
            Username: <form:input type="text" path="username" /> <span style="color:red;"><form:errors path="username" /></span><br/>
            Password: <form:input type="password" path="password" /> <span style="color:red;"><form:errors path="password" /></span><br/>
            First Name: <form:input type="text" path="firstName" /> <span style="color:red;"><form:errors path="firstName" /></span><br/>
            Last Name: <form:input type="text" path="lastName" /> <span style="color:red;"><form:errors path="lastName" /></span><br/>
            Email: <form:input type="text" path="email" /> <span style="color:red;"><form:errors path="email" /></span><br/>
            Phone: <form:input type="text" path="phone" /> <span style="color:red;"><form:errors path="phone" /></span><br/><br/>
            <input type="submit" name="create-user" value="Submit" />
        </form:form>
        <hr/>
        <form action="login.htm" method="get">
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
