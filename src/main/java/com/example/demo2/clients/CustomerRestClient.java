package com.example.demo2.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo2.entities.Customer;

public class CustomerRestClient extends BaseRestClient 
{
	
	public CustomerRestClient(RestTemplate template) 
	{
		super("http://localhost:8081/v1/api/manage/customer", template);
	}
	
	public List<Customer> getAllCustomers()
	{
		final String action = getBaseUrl()+"/all";
		ResponseEntity<Customer[]> response = getTemplate().getForEntity(
				  action,
				  Customer[].class);
		Customer[] customers = response.getBody();
		return Arrays.asList(customers);
	}
	
	public Customer getCustomerById(long customerId)
	{
		final String action = getBaseUrl()+"/id/%d".formatted(customerId);
		Customer customer = getTemplate().getForObject(action, Customer.class);
		return customer;
	}
	
	public Customer getCustomerByPhoneNumber(String phoneNumber)
	{
		final String action = getBaseUrl()+"/phone/%s".formatted(phoneNumber);
		Customer customer = getTemplate().getForObject(action, Customer.class);
		return customer;
	}
	
	public Customer saveOrUpdate(Customer customer)
	{
		final String action = getBaseUrl();
		HttpEntity<Customer> request = new HttpEntity<Customer>(customer);
		Customer c = getTemplate().postForObject(action, request, Customer.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + c);
		return customer;
	}
	
	public void deleteById(long customerId)
	{
		final String action = getBaseUrl()+"/delete/%d".formatted(customerId);
		getTemplate().delete(action);
	}
	
}
