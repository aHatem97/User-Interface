package com.example.demo2.services;

import com.example.demo2.clients.UserRestClient;
import com.example.demo2.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import javax.annotation.PostConstruct;  

@Service
public class UserService 
{

@Autowired
RestTemplate restTemplate;

private UserRestClient client;

@PostConstruct
private void init() 
{
	client = new UserRestClient(restTemplate);
}

/** Getting All Users
 * 
 * @return
 */
public List<User> getAllUsers()   
{  
	List<User> user = client.getAllUsers(); 
	return user;
}  

/** Getting A Specific User By Username
 * 
 * @param username
 * @return
 * @throws Exception
 */
public User getUserByUsername(String username) throws Exception 
{
	return client.getUserByUsername(username);
}

/** Getting A Specific User By User ID
 * 
 * @param userId
 * @return
 */
public User getUserById(long userId)   
{  
	return client.getUserById(userId);
}  

/** Saving A Specific User
 * 
 * @param user
 * @return
 */
public User saveOrUpdate(User user)   
{  
	return client.saveOrUpdate(user);  
}  

/** Deleting A Specific User By User ID
 * 
 * @param userId
 */
public void delete(long userId)   
{  
	client.deleteById(userId);  
}  

}