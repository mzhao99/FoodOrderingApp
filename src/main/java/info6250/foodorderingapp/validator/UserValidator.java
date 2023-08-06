/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.validator;

import info6250.foodorderingapp.dao.UserDao;
import info6250.foodorderingapp.pojo.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author manlingzhao
 */
@Component
public class UserValidator implements Validator{
    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object command, Errors errors) {
        User user = (User) command;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty-username", "Username cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Password cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty-firstName", "First Name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty-lastName", "Last Name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty-email", "Email cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "empty-phone", "Phone cannot be empty");
        //phone validation
        try { 
            Integer.parseInt(user.getPhone()); 
        } catch(Exception e) { 
            errors.rejectValue("phone", "phone-digit", "Phone number must only contain digits");
        }
        if (user.getPhone().length() != 10)    errors.rejectValue("phone", "phone-length", "Phone must be 10 digits");
        //email validation
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches())    errors.rejectValue("email", "email-check", "Email invalid");
        //username validation
        UserDao userdao = new UserDao();
        if (!userdao.isUniqueUsername(user.getUsername()))    errors.rejectValue("username", "username-nonunique", "Username taken, please choose another one");
    }  
}
