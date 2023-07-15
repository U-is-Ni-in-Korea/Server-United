package com.universe.uni.service;

import static com.universe.uni.exception.dto.ErrorType.*;
import static java.util.Objects.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.universe.uni.domain.entity.User;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.exception.UnauthorizedException;
import com.universe.uni.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserUtil {
	private final UserRepository userRepository;

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (isNull(authentication) || isNull(authentication.getPrincipal())) {
			throw new UnauthorizedException(EMPTY_SECURITY_CONTEXT);
		}
		return getUserById((Long)authentication.getPrincipal());
	}

	private User getUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));
	}
}
