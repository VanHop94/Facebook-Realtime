package com.datasection.web.mvc.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener
{
	private static final Logger log = Logger.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent event)
	{
//		event.getSession().setMaxInactiveInterval(30);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{
		log.info("==== Session is destroyed ====");
	}
}