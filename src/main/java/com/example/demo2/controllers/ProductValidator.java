package com.example.demo2.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.client.RestTemplate;

import com.example.demo2.clients.ProductRestClient;
import com.example.demo2.entities.Product;
import com.example.demo2.services.ProductService;

@Component
public class ProductValidator implements Validator 
{
	@Autowired
	ProductService productService;
	
	@Autowired
	RestTemplate restTemplate;

	private ProductRestClient client;

	@PostConstruct
	private void init() 
	{
		client = new ProductRestClient(restTemplate);
	}
	
    @Override
    public boolean supports(Class<?> aClass) 
    {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) 
    {
        Product product = (Product) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "NotEmpty");
        if (product.getProductName().length() < 8 || product.getProductName().length() > 20) 
        {
            errors.rejectValue("productName", "Size.Product.productName");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotNull");
        if (product.getPrice() < 0 || product.getPrice() > 10000) 
        {
            errors.rejectValue("price", "Value.Product.price");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posTerminalId", "NotEmpty", "Duplicate");
        try 
        {
			if (product.getProductId() == 0L & client.getProductByPosTerminalId(product.getPosTerminalId()) != null) 
			{
			    errors.rejectValue("posTerminalId", "Duplicate.Product.posTerminalId");
			}
		} catch (Exception e) 
        {
			e.printStackTrace();
		}
    }
}