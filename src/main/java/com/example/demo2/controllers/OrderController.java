package com.example.demo2.controllers;

import com.example.demo2.entities.Customer;
import com.example.demo2.entities.Order;
import com.example.demo2.entities.Product;
import com.example.demo2.entities.dto.OrderConfirmRequest;
import com.example.demo2.entities.dto.OrderConfirmResponse;
import com.example.demo2.services.CustomerService;
import com.example.demo2.services.OrderService;
import com.example.demo2.services.ProductService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController 
{

@Autowired
OrderService orderService;

@Autowired
CustomerService consumerService;

@Autowired
ProductService productService;

/** Creating A Get Mapping That Retrieves All The Order Details From The Database
 * 
 * @return
 */
@GetMapping
public List<Order> getAllOrders()
{
    return orderService.getAllOrders();
}

/** Creating A Get Mapping That Retrieves The Details Of A Specific Order
 *   
 * @param orderId
 * @return
 */
@GetMapping("/{orderId}")  
private Order getOrder(@PathVariable("orderId") long orderId)   
{  
	return orderService.getOrderById(orderId);  
}  

/** Creating A Delete Mapping That Deletes A Specified Order 
 *  
 * @param orderId
 */
@DeleteMapping("/{orderId}")  
private void deleteOrder(@PathVariable("orderId") long orderId)   
{  
	orderService.delete(orderId);  
}  

/** Creating Post Mapping That Saves The Order Details In The Database 
 *  
 * @param order
 * @return
 * @throws Exception
 */
//@PostMapping("/confirm") 
//private OrderConfirmResponse saveOrder(@RequestBody OrderConfirmRequest req)   
//{  
//	try 
//	{
//		
//		Customer consumer = null;
//		Product product = productService.getProductById(req.getProduct_id());
//		try 
//		{
//		   consumer = consumerService.getCustomerByPhoneNumber("01012645677");
//		}catch(Exception e) 
//		{
//		   consumer = new Customer("Nour Kassar", "01012645677");
//		   consumerService.saveOrUpdate(consumer);
//		}
//		Order order = new Order(consumer, product, req.getNotes(), req.getAmount());
//		order = orderService.saveOrUpdate(order);
//		return new OrderConfirmResponse("Success","0000");
//		
//	}catch(Exception e) 
//	{
//		return new OrderConfirmResponse("fail","9999");
//	}
//
//}  

/** Creating Put Mapping That Updates The Order Details
 * 
 * @param order
 * @return
 */
@PutMapping
private Order saveOrUpdate(@RequestBody Order order)   
{  
	orderService.saveOrUpdate(order);  
	return order;  
}  

}