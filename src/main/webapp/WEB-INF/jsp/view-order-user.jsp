<%-- 
    Document   : view-order-user
    Created on : Jul 2, 2023, 11:09:20 AM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders</title>
    </head>
    <body>
        <h1>My Orders</h1>
        <form>
            <input type="hidden" name="un" value=${un}>
            <input type="hidden" name="lst" list="${lst}">
            <c:forEach var="allOrders" items="${lst}">
                <c:set var="total" value="${0}"/>
                <c:forEach var="orders" items="${allOrders.value}">
                    <b><c:out value="${orders.key}"/></b><br/>
                    <c:out value="${orders.value[0].getOrderTime().toString().substring(0, 16)} - " />
                    <c:out value="${orders.value[0].getOrderStatus()}"/>
                    <ul>
                        <c:forEach var="o" items="${orders.value}">
                            <c:set var="total" value="${total + o.getItemPrice()}" />
                            <li><c:out value="${o.getItemName()} - $${o.getItemPrice()}"/></li>
                        </c:forEach>
                    </ul>
                    <c:out value="Total: $"/>
                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}"/>
                    <hr>
                </c:forEach>
            </c:forEach>
        </form>
        <form action="login.htm" method="post">
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="password" value=${password} />
            <input type="hidden" name="user-type" value=${usertype} />
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
