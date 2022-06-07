package com.example.demo2.entities;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Product
{
	
//
	protected long productId;
	protected String posTerminalId;
	protected String productName;
	protected long price;
	protected boolean isReservable;
	protected boolean isPayable;
	protected User user;
	protected List<Customer> customer;

//

public Product() {}; //default constructor    

public Product(String productName, long price, String posTerminalId, boolean isReservable, boolean isPayable, User user) 
{
    this.productName = productName;
    this.price = price;
    this.posTerminalId = posTerminalId;
    this.isReservable = isReservable;
    this.isPayable = isPayable;
    this.user = user;
}

public long getProductId() 
{
	return productId;
}

public void setProductId(long productId) 
{
	this.productId = productId;
}

public String getProductName() 
{
	return productName;
}

public void setProductName(String productName) 
{
	this.productName = productName;
}

public long getPrice() 
{
	return price;
}

public void setPrice(long price) 
{
	this.price = price;
}

public boolean getIsReservable() 
{
	return isReservable;
}

public void setIsReservable(boolean isReservable) 
{
	this.isReservable = isReservable;
}

public boolean getIsPayable() 
{
	return isPayable;
}

public void setIsPayable(boolean isPayable) 
{
	this.isPayable = isPayable;
}

public String getPosTerminalId() 
{
	return posTerminalId;
}

public void setPosTerminalId(String posTerminalId) 
{
	this.posTerminalId = posTerminalId;
}

public User getUser() 
{
	return user;
}

public void setUser(User user) 
{
	this.user = user;
}

@Override
public String toString() 
{
	return "Product [productId=" + productId + ", posTerminalId=" + posTerminalId + ", productName=" + productName
			+ ", price=" + price + ", isReservable=" + isReservable + ", isPayable=" + isPayable + ", user=" + user
			+ ", customers=" + customer + "]";
}

}