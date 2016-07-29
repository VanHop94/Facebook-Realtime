package com.datasection.facebook.thread.crawler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.datasection.web.mvc.config.MvcConfiguration;

@ContextConfiguration(classes = { MvcConfiguration.class }, loader = AnnotationConfigContextLoader.class)
public class MainController {

	public static void main(String [] args){
		
		AnnotationConfigApplicationContext ctx =
			     new AnnotationConfigApplicationContext();
			 ctx.register(MvcConfiguration.class);
			 ctx.refresh();
		
		ThreadQueryDb thread = ctx.getBean(ThreadQueryDb.class);
		thread.start();
		
	}
	
}
