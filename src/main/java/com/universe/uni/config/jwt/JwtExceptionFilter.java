package com.universe.uni.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.universe.uni.exception.ApiException;
import com.universe.uni.exception.dto.ErrorResponse;

import lombok.val;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (ApiException exception) {
			setResponse(response, exception);
		}
	}

	private void setResponse(
		HttpServletResponse response,
		ApiException exception
	) throws IOException {
		val objectMapper = new ObjectMapper();
		val errorResponse = ErrorResponse.businessErrorOf(exception.getError());
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}
}
