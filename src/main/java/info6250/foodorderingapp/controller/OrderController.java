/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.controller;

import info6250.foodorderingapp.dao.OrderDao;
import info6250.foodorderingapp.dao.RestaurantDao;
import info6250.foodorderingapp.dao.UserDao;
import info6250.foodorderingapp.pojo.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author manlingzhao
 */
@Controller
public class OrderController {
    @PostMapping("/view-order-user.htm")
    public String viewOrderUser(HttpServletRequest request, UserDao userdao, OrderDao orderdao){
        String un = request.getParameter("un"); 
        request.setAttribute("un", un); //pass down
        request.setAttribute("username", userdao.getUser(un).getUsername());
        request.setAttribute("password", userdao.getUser(un).getPassword());
        request.setAttribute("usertype", "non-owner");
        
        List<Order> lst = orderdao.getUserOrders(un);
        //group orders by orderId and restaurant
        Map<Integer, Map<String, List<Order>>> map = new HashMap<>();
        for (Order o : lst){
            String resName = o.getResName();
            int orderId = o.getOrderId();
            
            if (map.containsKey(orderId)){
                if (map.get(orderId).containsKey(resName)){
                    map.get(orderId).get(resName).add(o);
                }else{
                    List<Order> new_lst = new ArrayList<>();
                    new_lst.add(o);
                    map.get(orderId).put(resName, new_lst);  
                }
            }else{
                List<Order> new_lst = new ArrayList<>();
                new_lst.add(o);
                Map<String, List<Order>> inner_map = new HashMap<>();
                inner_map.put(resName, new_lst);
                map.put(orderId, inner_map);
            }
        }
        request.setAttribute("lst", map);
        return "view-order-user";
    }
    
    @PostMapping("/view-order-res.htm")
    public String viewOrderRes(HttpServletRequest request, RestaurantDao resdao, OrderDao orderdao){
        String resId = request.getParameter("resId");
        request.setAttribute("resId", resId);
        request.setAttribute("username", resdao.getRestaurant(resId).getUsername());
        request.setAttribute("password", resdao.getRestaurant(resId).getPassword());
        request.setAttribute("usertype", "owner");
        
        List<Order> lst = orderdao.getResOrders(resId);
        //group orders by order id
        Map<Integer, List<Order>> orders = new HashMap();
        for (Order o : lst){
            int orderId = o.getOrderId();
            if (orders.containsKey(orderId)){
                orders.get(orderId).add(o);
            }else{
                List<Order> new_lst = new ArrayList<>();
                new_lst.add(o);
                orders.put(orderId, new_lst);
            }
        }
        request.setAttribute("lst", orders);

        return "view-order-res";
    }
    
    @PostMapping("/update-order.htm")
    public String updateOrderStatus(HttpServletRequest request, OrderDao orderdao){
        String orderId = request.getParameter("single-order");
        String resId = request.getParameter("resId");
        request.setAttribute("resId", resId);
        
        if (request.getParameter("in-progress") != null){
            orderdao.updateStatus(orderId, resId, "In Progress"); 
        }
        else if (request.getParameter("complete") != null){
            orderdao.updateStatus(orderId, resId, "Completed");
        }
        return "order-update-success";
    }
}
