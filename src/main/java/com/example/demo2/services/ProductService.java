package com.example.demo2.services;

import com.example.demo2.clients.ProductRestClient;
import com.example.demo2.entities.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import javax.annotation.PostConstruct;  

@Service
public class ProductService 
{

@Autowired
RestTemplate restTemplate;

private ProductRestClient client;

@PostConstruct
private void init() 
{
	client = new ProductRestClient(restTemplate);
}


/** Getting All Products
 * 
 * @return
 */
public List<Product> getAllProducts()   
{  
	List<Product> product = client.getAllProducts(); 
	return product;
}  

/** Getting A Specific Product By Product Name
 * 
 * @param productName
 * @return
 * @throws Exception
 */
public Product getProductByProductName(String productName) throws Exception 
{
	return client.getProductByProductName(productName);
}

/** Getting A Specific Product By PosTerminalId
 * 
 * @param posTerminalId
 * @return
 * @throws Exception
 */
public Product getProductByPosTerminalId(String posTerminalId) throws Exception
{
	return client.getProductByPosTerminalId(posTerminalId);
}

/** Getting A Specific Product By Using Product ID
 * 
 * @param productId
 * @return
 */
public Product getProductById(long productId)   
{  
	return client.getProductById(productId);
}  

/** Saving or Updating A Specific Product
 * 
 * @param product
 * @return
 */
public Product saveOrUpdate(Product product)   
{  
	return client.saveOrUpdate(product);  
}  

/** Deleting A Specific Product By Product ID
 * 
 * @param productId
 */
public void delete(long productId)   
{  
	client.deleteById(productId);  
}  

}