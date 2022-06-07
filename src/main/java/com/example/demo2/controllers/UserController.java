package com.example.demo2.controllers;

import com.example.demo2.entities.User;
import com.example.demo2.services.UserService;
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
@RequestMapping(path = "/api/v1/manage/user")
public class UserController 
{

@Autowired
UserService userService;

/** Creating A Get Mapping That Retrieves All The User Details From The Database
 * 
 * @return
 */
@GetMapping
public List<User> getAllUsers()
{
    return userService.getAllUsers();
}

/** Creating A Get Mapping That Retrieves The Details Of A Specific User
 *   
 * @param userId
 * @return
 */
@GetMapping("/{userId}")  
private User getUser(@PathVariable("userId") long userId)   
{  
	return userService.getUserById(userId);  
}  

/** Creating A Delete Mapping That Deletes A Specified User 
 *  
 * @param userID
 */
@DeleteMapping("/{userId}")  
private void deleteUser(@PathVariable("userId") long userId)   
{  
	userService.delete(userId);  
}  

/** Creating Post Mapping That Saves The User Details In The Database 
 *  
 * @param user
 * @return
 * @throws Exception
 */
@PostMapping 
private long saveUser(@RequestBody User user) throws Exception   
{  
	try 
	{
		User u = userService.saveOrUpdate(user);
		return u.getUserId();
	}catch(Exception e) 
	{
		throw new Exception("test");
	}
}  

/** Creating Put Mapping That Updates The User Details
 * 
 * @param user
 * @return
 */
@PutMapping
private User update(@RequestBody User user)   
{  
	userService.saveOrUpdate(user);  
	return user;  
}  

}