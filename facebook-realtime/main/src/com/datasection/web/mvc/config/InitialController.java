package com.datasection.web.mvc.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InitialController
 */
@WebListener
public class InitialController implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public InitialController() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("finish");
	}

}
