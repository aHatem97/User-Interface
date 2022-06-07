package com.example.demo2.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo2.entities.Order;

public class OrderRestClient extends BaseRestClient 
{
	
	public OrderRestClient(RestTemplate template) 
	{
		super("http://localhost:8081/v1/api/manage/order", template);
	}
	
	public List<Order> getAllOrders()
	{
		final String action = getBaseUrl()+"/all";
		ResponseEntity<Order[]> response = getTemplate().getForEntity(
				  action,
				  Order[].class);
		Order[] orders = response.getBody();
		return Arrays.asList(orders);
	}
	
	public List<Order> getAllOrders(int pageNo, int pageSize, String sortField, String sortDirection)
	{
		final String action = getBaseUrl()+"/all/page/%d/%d/sort/%s/%s".formatted(pageNo, pageSize, sortField, sortDirection);
		ResponseEntity<Order[]> response = getTemplate().getForEntity(
				  action,
				  Order[].class);
		Order[] orders = response.getBody();
		return Arrays.asList(orders);
	}
	
	public int getAllOrdersSize()
	{
		final String action = getBaseUrl()+"/all/size";
		Integer size = getTemplate().getForObject(action, Integer.class);
		return size;
	}
	
	public Order getOrderById(Long orderId)
	{
		final String action = getBaseUrl()+"/id/%d".formatted(orderId);
		Order order = getTemplate().getForObject(action, Order.class);
		return order;
	}
	
	public Order getOrderByTrxId(String trxid)
	{
		final String action = getBaseUrl()+"/trx/%s".formatted(trxid);
		Order order = getTemplate().getForObject(action, Order.class);
		return order;
	}
	
	public void saveOrUpdate(Order order)
	{
		final String action = getBaseUrl();
		HttpEntity<Order> request = new HttpEntity<Order>(order);
		Long o = getTemplate().postForObject(action, request, Long.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + o);
	}
	
	public void deleteById(Long orderId)
	{
		final String action = getBaseUrl()+"/delete/%d".formatted(orderId);
		getTemplate().delete(action);
	}
	
}
