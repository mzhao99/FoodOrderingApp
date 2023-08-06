/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.controller;

import info6250.foodorderingapp.dao.RestaurantDao;
import info6250.foodorderingapp.dao.UserDao;
import info6250.foodorderingapp.pojo.Restaurant;
import info6250.foodorderingapp.pojo.User;
import info6250.foodorderingapp.validator.LoginValidator;
import info6250.foodorderingapp.validator.RestaurantValidator;
import info6250.foodorderingapp.validator.UserValidator;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author manlingzhao
 */
@Controller
public class LoginController {
    @Autowired
    LoginValidator loginValidator;
    
    @Autowired
    UserValidator  userValidator;
    
    @Autowired
    RestaurantValidator restaurantValidator;
    
    @GetMapping("/login.htm")
    public String loginForm(ModelMap model) {
        model.addAttribute("login", new User());
        return "login";
    }
    
    @PostMapping("/login.htm")
    public String loginCheck(@ModelAttribute("login") User user, BindingResult result, SessionStatus status, HttpServletRequest request, UserDao userdao, RestaurantDao resdao) {
//        loginValidator.validate(user, result);
        if (result.hasErrors()) {
            return "login";
        } 
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isOwner = false;
        if (request.getParameter("user-type").equals("owner"))    isOwner = true;

        if (!isOwner && userdao.checkLogin(username, password)){
            request.setAttribute("firstName", userdao.getUser(username).getFirstName());
            request.setAttribute("lastName", userdao.getUser(username).getLastName());
            request.setAttribute("un", username);
            List<Restaurant> lst = resdao.getAllRestaurants();
            request.setAttribute("resList", lst);   
            return "user-home";
        }else if (isOwner && resdao.checkLogin(username, password)){
            request.setAttribute("restaurantId", resdao.getId(username));
            request.setAttribute("restaurantName", resdao.getName(username));
            return "restaurant-home";
        }
        else    return "login";
    }
    
    @GetMapping("/user-create.htm")
    public String userCreateForm(ModelMap model) {
        model.addAttribute("userCreate", new User());
        return "login-user-create";
    }
    
    @PostMapping("/user-create.htm")
    public String userCreation(@ModelAttribute("userCreate") User user, BindingResult result, SessionStatus status, HttpServletRequest request, UserDao userdao) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "login-user-create";
        } 

        userdao.createUserLogin(user);
        return "acc-create-success";
    }
    
    @GetMapping("/restaurant-create.htm")
    public String resCreateForm(ModelMap model) {
        model.addAttribute("restaurantCreate", new Restaurant());
        return "login-res-create";
    }
    
    @PostMapping("/restaurant-create.htm")
    public String resCreation(@ModelAttribute("restaurantCreate") Restaurant res, BindingResult result, SessionStatus status, HttpServletRequest request, RestaurantDao resdao) {
        restaurantValidator.validate(res, result);
        if (result.hasErrors()) {
            return "login-res-create";
        } 
        
        Random rand = new Random();
        int id = rand.nextInt(99999999);
        res.setId(id);
        resdao.createRestLogin(res);
        return "acc-create-success";
    }
}
