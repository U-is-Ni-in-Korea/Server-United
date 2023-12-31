package com.universe.uni.config.jwt;

import static java.util.Objects.*;

import com.universe.uni.domain.entity.User;
import com.universe.uni.exception.ApiException;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.repository.UserRepository;
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
	private final UserRepository userRepository;

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
			// 임시로 커플아이디 확인 로직 추가
			if (!isNotRequiredCoupleIdPath(uri)) {
				User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorType.USER_NOT_EXISTENT));
				if (user.getCouple() == null) {
					throw new NotFoundException(ErrorType.COUPLE_NOT_EXISTENT);
				}
			}
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

	private boolean isNotRequiredCoupleIdPath(String uri) {
		return uri.contains("user") || uri.contains("couple");
	}
}
