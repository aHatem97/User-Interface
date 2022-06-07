package com.example.demo2.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo2.entities.User;

public class UserRestClient extends BaseRestClient 
{
	
	public UserRestClient(RestTemplate template) 
	{
		super("http://localhost:8081/v1/api/manage/user", template);
	}
	
	public List<User> getAllUsers()
	{
		final String action = getBaseUrl()+"/all";
		ResponseEntity<User[]> response = getTemplate().getForEntity(
				  action,
				  User[].class);
		User[] users = response.getBody();
		return Arrays.asList(users);
	}
	
	public User getUserById(Long userId)
	{
		final String action = getBaseUrl()+"/id/%d".formatted(userId);
		User user = getTemplate().getForObject(action, User.class);
		return user;
	}
	
	public User getUserByUsername(String username)
	{
		final String action = getBaseUrl()+"/name/%s".formatted(username);
		User user = getTemplate().getForObject(action, User.class);
		return user;
	}
	
	public User saveOrUpdate(User user)
	{
		final String action = getBaseUrl();
		HttpEntity<User> request = new HttpEntity<User>(user);
		User u = getTemplate().postForObject(action, request, User.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + u);
		return user;
	}
	
	public void deleteById(Long userId)
	{
		final String action = getBaseUrl()+"/delete/%d".formatted(userId);
		getTemplate().delete(action);
	}
	
}
