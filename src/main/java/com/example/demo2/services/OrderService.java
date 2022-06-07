package com.example.demo2.services;

import com.example.demo2.clients.OrderRestClient;
import com.example.demo2.entities.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import javax.annotation.PostConstruct;  

@Service
public class OrderService 
{

@Autowired
RestTemplate restTemplate;

private OrderRestClient client;

@PostConstruct
private void init() 
{
	client = new OrderRestClient(restTemplate);
}

/** Getting All Orders
 * 
 * @return
 */
public List<Order> getAllOrders()   
{  
	List<Order> order = client.getAllOrders(); 
	return order; 
}  

/** Getting A Specific Order By Order ID
 * 
 * @param orderId
 * @return
 */
public Order getOrderById(long orderId)   
{  
	return client.getOrderById(orderId);
}  

/** Getting A Specific Order By trxid
 * 
 * @param trxid
 * @return
 * @throws Exception
 */
public Order getOrderByTrxId(String trxid)
{
	return client.getOrderByTrxId(trxid);
}

/** Saving A Specific Order
 * 
 * @param order
 * @return
 */
public void saveOrUpdate(Order order)   
{  
	client.saveOrUpdate(order);  
}  

/** Deleting A Specific Order By Order ID
 * 
 * @param orderId
 */
public void delete(long orderId)   
{  
	client.deleteById(orderId);  
}  

}