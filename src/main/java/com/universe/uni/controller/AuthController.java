package com.universe.uni.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.service.AuthServiceContract;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

	private final AuthServiceContract authService;

	@GetMapping("kakao")
	public AuthTokenDto redirectKakaoAuth(@RequestParam(name = "code") String authenticationCode) {
		return authService.authWithKakao(authenticationCode);
	}
}
