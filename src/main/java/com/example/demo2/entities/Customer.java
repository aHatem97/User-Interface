package com.example.demo2.entities;

import java.util.List;
import java.util.function.BooleanSupplier;

import org.springframework.stereotype.Component;

@Component
public class Customer
{
	
//
	protected long customerId;
	protected String name;
	protected String phoneNumber;
    protected List<Product> product;

//
    
public Customer() {} //default constructor

public Customer(String name, String phoneNumber) 
{
    this.name = name;
    this.phoneNumber = phoneNumber;
}

public long getCustomerId() 
{
	return customerId;
}

public void setCustomerId(long customerId) 
{
	this.customerId = customerId;
}

public String getName() 
{
	return name;
}

public void setName(String name) 
{
	this.name = name;
}

public String getPhoneNumber() 
{
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) 
{
	this.phoneNumber = phoneNumber;
}

@Override
public String toString() 
{
	return "Customer [customerId=" + customerId + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
}

public BooleanSupplier matches(String customerName) 
{
	// TODO Auto-generated method stub
	return null;
}

}