package com.example.demo2.entities;

import org.springframework.stereotype.Component;

@Component
public class Order
{
	protected long orderId;
	protected Product product;
	protected Customer customer;
	protected String notes;
	protected long amount;
	protected String trxid;


public Order(long id) 
{
	customer = new Customer();
	product = new Product();
}
	
public Order() 
{
	customer = new Customer();
	product = new Product();
}

public Order(Customer customer,Product product, String note, long amount) 
{
	this.customer = customer;
	this.product = product;
	this.amount = amount;
	this.notes = note;
}

public Order(Customer customer,Product product, String note, long amount, String trxid) 
{
	this.customer = customer;
	this.product = product;
	this.amount = amount;
	this.notes = note;
	this.trxid = trxid;
}

public String getNotes() 
{
	return notes;
}

public void setNotes(String notes) 
{
	this.notes = notes;
}

public long getAmount() 
{
	return amount;
}

public void setAmount(long amount) 
{
	this.amount = amount;
}

public long getOrderId() 
{
	return orderId;
}

public void setOrderId(long orderId) 
{
	this.orderId = orderId;
}

public Product getProduct()
{
	return product;
}

public void setProduct(Product product) 
{
	this.product = product;
}

public Customer getCustomer() 
{
	return customer;
}

public void setCustomer(Customer customer) 
{
	this.customer = customer;
}

public String getTrxid() 
{
	return trxid;
}

public void setTrxid(String trxid) 
{
	this.trxid = trxid;
}

public String getProductId() 
{
	Long id = this.product.getProductId();
	return id.toString();
}

public void setProductId(String productId) 
{
	try 
	{
		this.product.setProductId(Long.parseLong(productId));
	}catch (Exception e) 
	{
		// TODO: handle exception
	}
}

}