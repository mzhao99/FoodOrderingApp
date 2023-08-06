/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.controller;

import info6250.foodorderingapp.dao.ItemDao;
import info6250.foodorderingapp.dao.RestaurantDao;
import info6250.foodorderingapp.pojo.Item;
import info6250.foodorderingapp.validator.ItemValidator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class MenuController {
    
    @Autowired
    ItemValidator validator;
    
    @GetMapping("/view-menu-res.htm")
    public String viewMenuAdmin(){
        return "view-menu-res";
    }
    
    @PostMapping("/view-menu-res.htm")
    public String viewMenu(HttpServletRequest request, ItemDao itemdao, RestaurantDao resdao){
        String resId = request.getParameter("resId");
        request.setAttribute("resId", resId);
        List<Item> lst = itemdao.getAllItems(request.getParameter("resId"));
        request.setAttribute("itemList", lst);   

        request.setAttribute("username", resdao.getRestaurant(resId).getUsername());
        request.setAttribute("password", resdao.getRestaurant(resId).getPassword());
        request.setAttribute("usertype", "owner");

        return "view-menu-res";
    }
    
    @GetMapping("/item-add.htm")
    public String showAddForm(HttpServletRequest request, ModelMap model) {
        request.setAttribute("resId", request.getParameter("resId"));
        model.addAttribute("items", new Item());
        return "item-add-form";
    }

    @PostMapping("/item-add.htm")
    public String addItem(@ModelAttribute("items") Item item, BindingResult result, SessionStatus status, ItemDao itemdao, HttpServletRequest request){
        validator.validate(item, result);
        if (result.hasErrors()) {
            return "item-add-form";
        }
        
        request.setAttribute("resId", request.getParameter("resId"));
        Random rand = new Random();
        int id = rand.nextInt(99999999);
        item.setId(id);
        item.setResId(Integer.parseInt(request.getParameter("resId")));

        try {
            //transfer the temporary file in the memory to a file in the filesystem
            String fileName = item.getPhoto().getOriginalFilename();
            File file = new File("/Users/manlingzhao/NetBeansProjects/FoodOrderingApp/src/main/webapp/assets/", item.getId() + ".jpg");
            item.getPhoto().transferTo(file);
            item.setPhotoFilePath(item.getId() + ".jpg");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            System.out.println("IllegalStateException: " + ex.getMessage());
        }

        status.setComplete();
        itemdao.addItem(item);
        
        return "item-add-success";
    }
    
    @GetMapping("/item-update-res.htm")
    public String modifyMenu(HttpServletRequest request, ItemDao itemdao, ModelMap model){
        request.setAttribute("resId", request.getParameter("resId"));
        String id = request.getParameter("item");
        String deleteButton = request.getParameter("deleteItem");
        String updateButton = request.getParameter("updateItem");
        
        if (deleteButton != null){
            itemdao.deleteItem(id);
            return "item-delete-success";
        }else if (updateButton != null){
            Item i = itemdao.getItem(id);
            model.addAttribute("updateItem", i);
            request.setAttribute("itemId", id);
            return "item-update-form";
        }else{
            return "view-menu-res";
        }
    }
    
    @PostMapping("/item-update-res.htm")
    public String updateMenu(@ModelAttribute("updateItem") Item item, BindingResult result, SessionStatus status, HttpServletRequest request, ItemDao itemdao){
        validator.validate(item, result);
        if (result.hasErrors()) {
            return "item-update-form";
        }
        
        status.setComplete();
        String id = request.getParameter("itemId");
        String resId = request.getParameter("resId");
        item.setId(Integer.parseInt(id));
        item.setResId(Integer.parseInt(resId));
        try {
            String fileName = item.getPhoto().getOriginalFilename();
            File file = new File("/Users/manlingzhao/NetBeansProjects/FoodOrderingApp/src/main/webapp/assets/", item.getId() + ".jpg");
            item.getPhoto().transferTo(file);
            item.setPhotoFilePath(item.getId() + ".jpg");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            System.out.println("IllegalStateException: " + ex.getMessage());
        }
        itemdao.updateItem(item);
        request.setAttribute("resId", resId);
        return "item-update-success";
    }
}
