package com.example.demo2.controllers;

import com.example.demo2.entities.Product;
import com.example.demo2.services.ProductService;
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
@RequestMapping(path = "/api/manage/product")
public class ProductController 
{

@Autowired
ProductService productService;

/** Creating A Get Mapping That Retrieves All The Products Details From The Database 
 *   
 * @return
 */
@GetMapping
public List<Product> getAllProducts()
{
    return productService.getAllProducts();
}

/** Creating A Get Mapping That Retrieves The Detail Of A Specific Product 
 *  
 * @param productId
 * @return
 */
@GetMapping("/{productId}")  
private Product getProduct(@PathVariable("productId") long productId)   
{  
	return productService.getProductById(productId);  
}  

/** Creating A Delete Mapping That Deletes A Specified Product
 * 
 * @param productId
 */
@DeleteMapping("/{productId}")  
private void deleteProduct(@PathVariable("productId") long productId)   
{  
	productService.delete(productId);  
}  

/** Creating Post Mapping That Posts The Product Detail In The Database  
 * 
 * @param product
 * @return
 * @throws Exception
 */
@PostMapping 
private long saveProduct(@RequestBody Product product) throws Exception   
{  
	try {
		Product p = productService.saveOrUpdate(product);
		return p.getProductId();
	}catch(Exception e) {
		System.out.print(e.getMessage());
	}
	return 0;
}  

/** Creating Put Mapping That Updates The Product Detail
 * 
 * @param product
 * @return
 */
@PutMapping
private Product update(@RequestBody Product product)   
{  
	productService.saveOrUpdate(product);  
	return product;  
}  

}