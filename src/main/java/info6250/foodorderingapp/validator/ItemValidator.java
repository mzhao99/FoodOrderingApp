/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info6250.foodorderingapp.validator;

import info6250.foodorderingapp.pojo.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 *
 * @author manlingzhao
 */
@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> type) {
        return Item.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object command, Errors errors) {
        Item item = (Item) command;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty-name", "Name cannot be empty");

        if (item.getPrice() < 0)    errors.rejectValue("price", "neg-price", "Price cannot be negative");
    
        if (item.getPhoto() == null || item.getPhoto().getSize() == 0)
            errors.rejectValue("photo", "empty-file", "File is empty");
        
        if (!item.getPhoto().getOriginalFilename().endsWith(".jpg"))
            errors.rejectValue("photo", "file-type", "File must be .jpg");
    }
}
