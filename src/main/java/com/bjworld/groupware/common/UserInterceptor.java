package com.bjworld.groupware.common;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

public class UserInterceptor extends WebContentInterceptor {

	@Value("${spring.profiles.active}")
	private String profile;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {
		String path = request.getServletPath();

		if(path.equals("/join.do")) {
			
		}
		/* Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()) {
			String key = params.nextElement();
			String value = request.getParameter(key);
			
			System.out.println(key + " : " + value);
		}*/
		
		
		HandlerMethod method = (HandlerMethod) handler;

		LoginCheck loginCheckAnnotation = method.getMethodAnnotation(LoginCheck.class);

		if (loginCheckAnnotation == null) {
			loginCheckAnnotation = method.getBean().getClass().getAnnotation(LoginCheck.class);
		}

		if (loginCheckAnnotation != null) {
			
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView m)
			throws Exception {
		String path = request.getServletPath();
		
		if(path.endsWith("download.do"))
			return;
		
		if(!path.endsWith("Ajax.do")) {
		
		}
	}
}
