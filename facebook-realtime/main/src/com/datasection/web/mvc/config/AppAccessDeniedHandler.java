package com.datasection.web.mvc.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

public class AppAccessDeniedHandler extends AccessDeniedHandlerImpl {
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, AccessDeniedException exc)
			throws IOException, ServletException {
		super.handle(request, response, exc);
	}
}