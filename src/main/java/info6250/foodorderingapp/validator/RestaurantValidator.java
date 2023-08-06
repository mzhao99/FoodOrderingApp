/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.validator;

import info6250.foodorderingapp.dao.RestaurantDao;
import info6250.foodorderingapp.pojo.Restaurant;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author manlingzhao
 */
@Component
public class RestaurantValidator implements Validator{
    @Override
    public boolean supports(Class<?> type) {
        return Restaurant.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object command, Errors errors) {
        Restaurant restaurant = (Restaurant) command;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty-username", "Username cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Password cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty-name", "Restaurant name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cuisine", "empty-cuisine", "Cuisine cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "empty-phone", "Phone cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "empty-address", "Address cannot be empty");
        //phone validation
        try { 
            Integer.parseInt(restaurant.getPhone()); 
        } catch(Exception e) { 
            errors.rejectValue("phone", "phone-digit", "Phone number must only contain digits");
        }
        if (restaurant.getPhone().length() != 10)    errors.rejectValue("phone", "phone-length", "Phone must be 10 digits");
        //username validation
        RestaurantDao resdao = new RestaurantDao();
        if (!resdao.isUniqueUsername(restaurant.getUsername()))    errors.rejectValue("username", "username-nonunique", "Username taken, please choose another one");
    }  
}
