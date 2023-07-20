package com.universe.uni.config.jwt;

import static java.util.Objects.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.universe.uni.exception.UnauthorizedException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.service.JwtManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtManager jwtManager;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		val uri = request.getRequestURI();
		if (isContainApiPath(uri)) {
			String token = getJwtFromRequest(request);
			Long userId = jwtManager.getUserIdFromJwt(token);
			UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(userId, null, null);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {

		final String tokenType = "Bearer ";

		String header = request.getHeader("Authorization");

		if(isNull(header)) {
			throw new UnauthorizedException(ErrorType.TOKEN_VALUE_NOT_EXIST);
		}
		return header.substring(tokenType.length());
	}

	private boolean isContainApiPath(String uri) {
		return uri.startsWith("/api");
	}
}
