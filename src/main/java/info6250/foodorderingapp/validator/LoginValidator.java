/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.validator;

import info6250.foodorderingapp.dao.UserDao;
import info6250.foodorderingapp.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author manlingzhao
 */
@Component
public class LoginValidator implements Validator{
    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object command, Errors errors) {
        User user = (User) command;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty-username", "Username cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Password cannot be empty");
    
        UserDao userdao = new UserDao();
        if (!userdao.checkLogin(user.getUsername(), user.getPassword()))    errors.rejectValue("username", "not match", "Username or password incorrect");
    
    }    
}
