package com.yutian.fw;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


public class SpringContext
{
	
	private static WebApplicationContext context;
	
	private SpringContext(){
		
	}
	
	public static WebApplicationContext getInstance(){
		if(context == null){
			context = ContextLoader.getCurrentWebApplicationContext();
		}
		return context;
	}
	
	
	
}
