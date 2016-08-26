package com.javahash.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javahash.spring.config.DaoConfig;

public class DataSourceChangeInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		String dataSrc = request.getParameter("dataSrc") == null ? DaoConfig.CustomerContextHolder.getDefaultDataSource() : request.getParameter("dataSrc");
		DaoConfig.CustomerContextHolder.setType(dataSrc);
		return true;
	}
}
