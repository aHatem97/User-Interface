package com.example.demo2.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo2.entities.Product;

public class ProductRestClient extends BaseRestClient 
{
	
	public ProductRestClient(RestTemplate template) 
	{
		super("http://localhost:8081/v1/api/manage/product", template);
	}
	
	public List<Product> getAllProducts()
	{
		final String action = getBaseUrl()+"/all";
		ResponseEntity<Product[]> response = getTemplate().getForEntity(
				  action,
				  Product[].class);
				Product[] products = response.getBody();
		return Arrays.asList(products);
	}
	
	public List<Product> getAllProducts(int pageNo, int pageSize, String sortField, String sortDirection)
	{
		final String action = getBaseUrl()+"/all/page/%d/%d/sort/%s/%s".formatted(pageNo, pageSize, sortField, sortDirection);
		ResponseEntity<Product[]> response = getTemplate().getForEntity(
				  action,
				  Product[].class);
				Product[] products = response.getBody();
		return Arrays.asList(products);
	}
	
	public int getAllProductsSize()
	{
		final String action = getBaseUrl()+"/all/size";
		Integer size = getTemplate().getForObject(action, Integer.class);
		return size;
	}
	
	public Product getProductById(long productId)
	{
		final String action = getBaseUrl()+"/id/%d".formatted(productId);
		Product product = getTemplate().getForObject(action, Product.class);
		return product;
	}
	
	public Product getProductByProductName(String productName)
	{
		final String action = getBaseUrl()+"/name/%s".formatted(productName);
		Product product = getTemplate().getForObject(action, Product.class);
		return product;
	}
	
	public Product getProductByPosTerminalId(String posTerminalId)
	{
		final String action = getBaseUrl()+"/posTerminalId/%s".formatted(posTerminalId);
		Product product = getTemplate().getForObject(action, Product.class);
		return product;
	}
	
	public Product saveOrUpdate(Product product)
	{
		final String action = getBaseUrl();
		HttpEntity<Product> request = new HttpEntity<Product>(product);
		Product p = getTemplate().postForObject(action, request, Product.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + p);
		return product;
	}
	
	public void deleteById(long productId)
	{
		final String action = getBaseUrl()+"/delete/%d".formatted(productId);
		getTemplate().delete(action);
	}
	
}
