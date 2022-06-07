package com.example.demo2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Demo2Application 
{
	public static void main(String[] args) 
	{
		SpringApplication application = new SpringApplication(Demo2Application.class);
	    Map<String, Object> map = new HashMap<String,Object>();
	    map.put("server.servlet.context-path", "/v1");
	    map.put("server.port", "8080");
	    application.setDefaultProperties(map);
	    application.run(args);
	}
	
	@Bean
	public ClassLoaderTemplateResolver secondaryTemplateResolver() 
	{
	    ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
	    secondaryTemplateResolver.setPrefix("templates/pages/");
	    secondaryTemplateResolver.setSuffix(".html");
	    secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
	    secondaryTemplateResolver.setCharacterEncoding("UTF-8");
	    secondaryTemplateResolver.setOrder(1);
	    secondaryTemplateResolver.setCheckExistence(true);
	        
	    return secondaryTemplateResolver;
	}
}
