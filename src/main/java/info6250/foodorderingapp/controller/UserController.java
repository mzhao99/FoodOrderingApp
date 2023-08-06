/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.controller;

import info6250.foodorderingapp.dao.CartDao;
import info6250.foodorderingapp.dao.ItemDao;
import info6250.foodorderingapp.dao.OrderDao;
import info6250.foodorderingapp.dao.RestaurantDao;
import info6250.foodorderingapp.dao.UserDao;
import info6250.foodorderingapp.pojo.Cart;
import info6250.foodorderingapp.pojo.Item;
import info6250.foodorderingapp.pojo.Order;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author manlingzhao
 */
@Controller
public class UserController {
    @PostMapping("/selected-restaurant.htm")
    public String viewSelectedRestaurant(HttpServletRequest request, ItemDao itemdao, RestaurantDao resdao, UserDao userdao){
        String resId = request.getParameter("restaurant");
        String un = request.getParameter("un");
        List<Item> menu = itemdao.getAllItems(resId);
        String restaurantName = resdao.getRestaurant(resId).getName();
        
        request.setAttribute("un", un); //pass down
        request.setAttribute("resId", resId); // pass down
        request.setAttribute("menu", menu);
        request.setAttribute("resName", restaurantName);
        
        request.setAttribute("username", userdao.getUser(un).getUsername());
        request.setAttribute("password", userdao.getUser(un).getPassword());
        request.setAttribute("usertype", "non-owner");
        
        return "view-menu-user";
    }
    
    @PostMapping("/add-to-cart.htm")
    public String addItemToCart(HttpServletRequest request, ItemDao itemdao, CartDao cartdao, RestaurantDao resdao){
        String itemId = request.getParameter("item");
        String userId = request.getParameter("un");
        String resId = request.getParameter("resId");
        String itemName = itemdao.getItem(itemId).getName();
        double itemPrice = itemdao.getItem(itemId).getPrice();
        String resName = resdao.getRestaurant(resId).getName();
        
        request.setAttribute("itemName", itemName);
        request.setAttribute("un", userId); //pass down
        request.setAttribute("resId", resId); // pass down
        
        Random rand = new Random();
        int id = rand.nextInt(99999999);
        Cart c = new Cart(id, itemId, userId, resId, itemName, itemPrice, resName);
        cartdao.addToCart(c);
        return "cart-add-success";
    }
    
    @PostMapping("/view-cart.htm")
    public String viewCart(HttpServletRequest request, CartDao cartdao, RestaurantDao resdao, UserDao userdao){
        String username = request.getParameter("un");
        Map<String, List<Cart>> map = cartdao.getCart(username, resdao);
        double total = 0;
        
        for (String resName : map.keySet()){
            List<Cart> lst = map.get(resName);
            for (Cart c : lst){     
                total += c.getItemPrice(); 
            }
        }
        
        request.setAttribute("map", map);
        request.setAttribute("total", String.format("%.2f",total));
        request.setAttribute("username", username);
        request.setAttribute("password", userdao.getUser(username).getPassword());
        request.setAttribute("usertype", "non-owner");
        return "view-cart-user";
    }
    
    @PostMapping("/cart-action.htm")
    public String cartActionForm(HttpServletRequest request, CartDao cartdao, RestaurantDao resdao, OrderDao orderdao, ItemDao itemdao, UserDao userdao){
        request.setAttribute("un", request.getParameter("un"));
        String[] cartIds = request.getParameterValues("single-item");  
        
        if (request.getParameter("delete-item") != null){
            List<Cart> lst = new ArrayList<>();
            for (String id : cartIds){
                Cart c = cartdao.getOneCart(id);
                lst.add(c);
            }
            cartdao.deleteItemFromCart(lst);
            return "cart-delete-success";
        }else if (request.getParameter("checkout") != null){
            Random rand = new Random();
            int order_id = rand.nextInt(99999999);
            List<Cart> cart_lst = new ArrayList<>();
            List<Order> order_lst = new ArrayList<>();
            
            for (String cartId : cartIds){
                Cart c = cartdao.getOneCart(cartId);
                
                int id = rand.nextInt(99999);
                String resId = c.getResId();
                String itemId = c.getItemId();
                String userId = c.getUserId();
                String status = "Placed";
                Date date = new Date();
                Timestamp orderTime = new Timestamp(date.getTime());
                String itemName = itemdao.getItem(itemId).getName();
                double itemPrice = itemdao.getItem(itemId).getPrice();
                String resName = resdao.getRestaurant(resId).getName();
                String userFn = userdao.getUser(userId).getFirstName();
                        
                Order order = new Order(id, resId, itemId, userId, status, orderTime, order_id, itemName, itemPrice, resName, userFn);
                order_lst.add(order);
                cart_lst.add(c);
            }
            cartdao.deleteItemFromCart(cart_lst);
            orderdao.placeOrder(order_lst);
            return "order-checkout-success";
        }else{
            return "view-cart-user";
        }
    }
}
