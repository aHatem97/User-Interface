package com.example.demo2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo2.entities.Order;
import com.example.demo2.services.OrderService;

@Component
public class OrderValidator implements Validator 
{
	@Autowired
	OrderService orderService;
	
    @Override
    public boolean supports(Class<?> aClass) 
    {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) 
    {
        Order order = (Order) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "notes", "NotEmpty");
        if (order.getNotes().length() < 5 || order.getNotes().length() > 50) 
        {
            errors.rejectValue("notes", "Size.Order.notes");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "NotNull");
        if (order.getAmount() < 0 || order.getAmount() > 100) 
        {
            errors.rejectValue("amount", "Value.Order.amount");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "trxid", "NotEmpty");
        if (order.getTrxid().length() < 4 || order.getTrxid().length() > 20) 
        {
            errors.rejectValue("trxid", "Size.Order.trxid");
        }
        try 
        {
			if (order.getOrderId() == 0L & orderService.getOrderByTrxId(order.getTrxid()) != null) 
			{
			    errors.rejectValue("trxid", "Duplicate.Order.trxid");
			}
		} catch (Exception e) 
        {
			e.printStackTrace();
		}
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customer.phoneNumber", "NotEmpty");
        if (order.getCustomer().getPhoneNumber().length() != 11)
        {
            errors.rejectValue("customer.phoneNumber", "Size.Order.Customer.phoneNumber");
        }
    }
}