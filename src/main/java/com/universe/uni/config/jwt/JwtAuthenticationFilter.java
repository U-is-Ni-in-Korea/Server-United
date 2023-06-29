package com.universe.uni.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.universe.uni.service.JwtManager;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
		String token = getJwtFromRequest(request);

		if (jwtManager.verifyToken(token)) {
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
		if (header != null && header.startsWith(tokenType)) {
			return header.substring(tokenType.length());
		}
		log.error("UNSUPPORTED_JWT_TOKEN_TYPE");
		throw new JwtException("UNSUPPORTED_JWT_TOKEN_TYPE");
	}
}
