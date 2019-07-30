package com.example.xProject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {
	
	//로그인한 상태에서 특정 페이지로 넘어가려 하면(예:로그인,회원가입) 접근 제한
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		HttpSession session = request.getSession();		
		if(session.getAttribute("userid") != null) {
			response.sendRedirect(request.getContextPath()+"/?message=alreadylogin");
			return false; 
		}else {
			return true; 
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {		
		super.postHandle(request, response, handler, modelAndView);
	}
}
