package com.example.demo2.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo2.entities.Customer;
import com.example.demo2.entities.Order;
import com.example.demo2.entities.Product;
import com.example.demo2.services.CustomerService;
import com.example.demo2.services.OrderService;
import com.example.demo2.services.ProductService;

@Controller
@RequestMapping(value = "/ui/orders")
public class OrderTableController 
{
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
    @Autowired
    private OrderValidator orderValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String viewOrders(Model model) 
    {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @RequestMapping(value = {"/add-order", "/add-order/{id}"}, method = RequestMethod.GET)
    public String addOrders(@PathVariable(required=false) String id, Order order,Model model) 
    {
    	List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
    	if(id != null) 
    	{
    		try 
    		{
    		   long oid = Long.parseLong(id);
    		   Order o = orderService.getOrderById(oid);
    		   if(o != null) 
    		   {
    			   model.addAttribute(o);
    			   model.addAttribute("Edit", true);
    		   }
    		}catch(Exception e) 
    		{
    			  return "add-order"; 
    		}
    	}
    	
        return "add-order";
    }

    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    @ResponseBody
    public String save(Order order, BindingResult result, RedirectAttributes atts, Model model, HttpServletRequest request, HttpServletResponse response) 
    {		
//    	orderValidator.validate(order, result);
//    	
//    	if (result.hasErrors()) 
//    	{
//    		atts.addAttribute("hasErrors", true);
//    		List<Product> products = productService.getAllProducts();
//    		model.addAttribute("products", products);
//            return "add-order";
//        }
    	
    	String notes = request.getParameter("notes");
    	String amount = request.getParameter("amount");
    	String trxid = request.getParameter("trxid");
    	String selectedProducts = request.getParameter("selectedProducts");
    	String phoneNumber = request.getParameter("phoneNumber");
    	
    	order.setNotes(notes);
    	long am=Long.parseLong(amount);
    	order.setAmount(am);
    	order.setTrxid(trxid);
    	long pid=Long.parseLong(selectedProducts);
    	order.getProduct().setProductId(pid);
    	order.getCustomer().setPhoneNumber(phoneNumber);
    	
    	Product p = productService.getProductById(pid);
    	order.setProduct(p);
    	
//		String productid = (order.getProductId());
//		long pid=Long.parseLong(productid);
//    	Product p = productService.getProductById(pid);
//    	order.setProduct(p);
    	
		Customer c;
		try 
		{
			c = customerService.getCustomerByPhoneNumber(phoneNumber);
			order.setCustomer(c);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		orderService.saveOrUpdate(order);
		
		System.out.println("Notes: "+notes+"Amount: "+amount+"TrxID: "+trxid+"Selected Products: "+selectedProducts+"Phone Number: "+phoneNumber);
        return "redirect:/ui/orders";
    }

    @GetMapping("/delete")
    public String deleteOrder(long id) 
    {
    	orderService.delete(id);
        return "redirect:/ui/orders";
    }
}
