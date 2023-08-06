<%-- 
    Document   : view-menu-user
    Created on : Jul 1, 2023, 1:15:51 AM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Menu</title>
    </head>
    <body>
        <h1>${resName}</h1>
        <form action="add-to-cart.htm" method="post">
            <input type="hidden" name="un" value=${un}>
            <input type="hidden" name="resId" value=${resId}>
            <table>
                <c:forEach var="value" items="${menu}">
                    <tr>
                        <td><input type="radio" name="item" value="${value.getId()}" required></td>
                        <td><b><c:out value="${value.getName()}"/></b></td>
                        <td><c:out value="$${value.getPrice()}"/></td>
                        <td><img src="<c:url value="/assets/${value.getPhotoFilePath()}"/>" width="140" height="100"/></td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <input type="submit" value="Add To Cart"/>
        </form>
        <hr/>
        <form action="login.htm" method="post">
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="password" value=${password} />
            <input type="hidden" name="user-type" value=${usertype} />
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
