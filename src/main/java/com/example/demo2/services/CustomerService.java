package com.example.demo2.services;

import com.example.demo2.clients.CustomerRestClient;
import com.example.demo2.entities.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import javax.annotation.PostConstruct;  

@Service
public class CustomerService 
{
	@Autowired
	RestTemplate restTemplate;

	private CustomerRestClient client;

	@PostConstruct
	private void init() 
	{
		client = new CustomerRestClient(restTemplate);
	}
/** Get All Customers
 * 
 * @return
 */
	public List<Customer> getAllCustomers()   
	{  
		List<Customer> customer = client.getAllCustomers(); 
		return customer;
	}  

/** Getting A Specific Customer By Customer Phone Number
 * 
 * @param phoneNumber
 * @return
 * @throws Exception
 */
public Customer getCustomerByPhoneNumber(String phoneNumber)
{
	return client.getCustomerByPhoneNumber(phoneNumber);
}


/** Getting A Specific Customer By Searching With Customer ID
 * 
 * @param customerId
 * @return
 */
public Customer getCustomerById(long customerId)   
{  
	return client.getCustomerById(customerId);
}  

/** Saving A Specific Customer
 *  
 * @param customer
 * @return
 */
public Customer saveOrUpdate(Customer customer)   
{  
	return client.saveOrUpdate(customer);  
}  

/** Deleting Specific Customer By Customer ID
 * 
 * @param customerId
 */
public void delete(long customerId)   
{  
	client.deleteById(customerId);  
}  

}