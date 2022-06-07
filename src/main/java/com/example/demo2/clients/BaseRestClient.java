package com.example.demo2.clients;

import org.springframework.web.client.RestTemplate;

public abstract class BaseRestClient 
{
	
	private String rootUrl;
	private RestTemplate template;
	
	public BaseRestClient(String RootUrl, RestTemplate template) 
	{
		this.rootUrl = RootUrl;
		this.template = template;
	}
	
	protected String getBaseUrl() 
	{
		return this.rootUrl;
	}
	
	protected RestTemplate getTemplate() 
	{
		return template;
	}

}
