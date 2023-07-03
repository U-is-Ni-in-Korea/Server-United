package com.universe.uni.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	/* TODO 토큰변수 추가 */

	private static final String AUTHORIZATION = "Authorization";

	@Override
	public boolean preHandle(
		HttpServletRequest request, HttpServletResponse response, Object handler
	) throws Exception {
		/* TODO 토큰 header exception 처리 */
		return true;
	}
}
