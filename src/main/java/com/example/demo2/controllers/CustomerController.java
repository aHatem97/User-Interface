package com.example.demo2.controllers;

import com.example.demo2.entities.Customer;
import com.example.demo2.services.CustomerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/manage/customer")
public class CustomerController 
{

@Autowired
CustomerService customerService;

/** Creating A Get Mapping That Retrieves All The Customers Details From The Database 
 *   
 * @return
 */
@GetMapping
public List<Customer> getAllCustomers()
{
    return customerService.getAllCustomers();
}

/** Creating A Get Mapping That Retrieves The Details Of A Specific Customer
 * 
 * @param customerId
 * @return
 */
@GetMapping("/{customerId}")  
private Customer getCustomer(@PathVariable("customerId") long customerId)   
{  
return customerService.getCustomerById(customerId);  
}  

/** Creating A Delete Mapping That Deletes A Specified Customer
 * 
 * @param customerId
 */
@DeleteMapping("/{customerId}")  
private void deleteCustomer(@PathVariable("customerId") long customerId)   
{  
	customerService.delete(customerId);  
}  

/** Creating Post Mapping That Posts The Customer Details In The Database
 * 
 * @param customer
 * @return
 * @throws Exception
 */
@PostMapping 
private long saveCustomer(@RequestBody Customer customer) throws Exception   
{  
	try 
	{
		Customer c = customerService.saveOrUpdate(customer);
		return c.getCustomerId();
	} catch(Exception e) {
		throw new Exception("test");
	}
}  

/** Creating Put Mapping That Updates The Customer Details
 * 
 * @param customer
 * @return
 */
@PutMapping
private Customer update(@RequestBody Customer customer)   
{  
	customerService.saveOrUpdate(customer);  
return customer;  
}  

}