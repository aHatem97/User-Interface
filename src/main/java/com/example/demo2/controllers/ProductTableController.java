package com.example.demo2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo2.entities.Product;
import com.example.demo2.entities.User;
import com.example.demo2.services.ProductService;
import com.example.demo2.services.UserService;

@Controller
@RequestMapping(path = "/ui/products")
public class ProductTableController 
{	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
    @Autowired
    private ProductValidator productValidator;
	
    @GetMapping
    public String viewProducts(Model model) 
    {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
	
    @GetMapping({"/add-product", "/add-product/{id}"})
    public String addProducts(@PathVariable(required=false) Long id, Product product,Model model) 
    {
    	if(id != null) {
    		try {
    		   Product p = productService.getProductById(id);
    		   if(p != null) {
    			   model.addAttribute(p);
    			   model.addAttribute("Edit", true);
    		   }
    		}catch(Exception e) {
    			  return "add-product"; 
    		}
    	}
    	
        return "add-product";
    }

    @PostMapping("/save")
    public String update(Product product, BindingResult result, Model model, RedirectAttributes atts) 
    {
    	productValidator.validate(product, result);
    	
    	if (result.hasErrors()) 
    	{
    		atts.addAttribute("hasErrors", true);
            return "add-product";
        }
        model.addAttribute("products", productService.getAllProducts());
    	String userName = "Ahmed";
    	
		try 
		{
			User user = userService.getUserByUsername(userName);
			product.setUser(user);
			System.out.println(product);
			productService.saveOrUpdate(product);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
        return "redirect:/ui/products";
    }
    
    @GetMapping("/delete")
    public String deleteProduct(long id) 
    {
    	productService.delete(id);
        return "redirect:/ui/products";
    }
}
