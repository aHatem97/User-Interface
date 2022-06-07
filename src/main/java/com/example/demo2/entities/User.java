package com.example.demo2.entities;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class User
{
	
//
	protected long userId;
	protected String username;
	protected String password;
    private String passwordConfirm;
	protected List<Product> products;
    private Set<Role> roles;

//
public User() {}; //default constructor

public User(String username, String password) 
{
    this.username = username;
    this.password = password;
}

public Long getUserId() 
{
    return userId;
}

public void setUserId(long userID) 
{
    this.userId = userID;
}

public String getUsername() 
{
    return username;
}
public void setUsername(String username) 
{
    this.username = username;
}

public String getPassword()
{
    return password;
}

public void setPassword(String password) 
{
    this.password = password;
}

public Set<Role> getRoles() 
{
	return roles;
}

public void setRoles(Set<Role> roles) 
{
	this.roles = roles;
}

public String getPasswordConfirm() 
{
	return passwordConfirm;
}

public void setPasswordConfirm(String passwordConfirm) 
{
	this.passwordConfirm = passwordConfirm;
}


}