package com.mark.backend.web.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = false;
		String contextPath = request.getContextPath();
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			response.sendRedirect(contextPath + "/admin/index");
			return false;
		}
		for (Cookie c : cookies) {
			if ("mark.com".equals(c.getName())) {
				c.getValue();
				flag = true;
				break;
			}
		}
		if (!flag) {
			response.sendRedirect(contextPath + "/admin/index");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
