<%-- 
    Document   : view-order-res
    Created on : Jun 29, 2023, 12:59:40 PM
    Author     : manlingzhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Orders</title>
    </head>
    <body>
        <h1>View All Orders</h1>
        <form action="update-order.htm" method="post">
            <input type="hidden" name="resId" value=${resId}>
            <c:forEach var="allOrders" items="${lst}">
                <input type="radio" name="single-order" value="${allOrders.value[0].getOrderId()}" required>
                <c:set var="total" value="${0}"/>
                <c:out value="${allOrders.value[0].getOrderTime().toString().substring(0, 16)}"/>
                <ul>
                    <c:forEach var="o" items="${allOrders.value}">
                        <c:set var="total" value="${total + o.getItemPrice()}" />
                        <li><c:out value="${o.getItemName()}"/></li>
                    </c:forEach>
                </ul>
                <c:out value="Customer: ${allOrders.value[0].getUserFn()}" /><br/>
                <c:out value="Status: ${allOrders.value[0].getOrderStatus()}"/><br/>
                <c:out value="Total: $"/>
                <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}"/>
                <hr/>
            </c:forEach>
            <input type="submit" name="in-progress" value="Order In Progress"/>
            <input type="submit" name="complete" value="Order Complete"/>
        </form>
        <br/>
        <form action="login.htm" method="post">
            <input type="hidden" name="username" value=${username} />
            <input type="hidden" name="password" value=${password} />
            <input type="hidden" name="user-type" value=${usertype} />
            <input type="submit" name="back" value="Back" />
        </form>
    </body>
</html>
