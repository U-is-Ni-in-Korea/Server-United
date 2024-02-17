package com.universe.uni.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.controller.docs.AuthControllerContract;
import com.universe.uni.dto.request.AuthRequestDto;
import com.universe.uni.dto.response.SnsAuthResponseDto;
import com.universe.uni.service.AuthServiceContract;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController implements AuthControllerContract {

	private final AuthServiceContract authService;

	@PostMapping("kakao")
	@Override
	public SnsAuthResponseDto authByKakao(@RequestBody AuthRequestDto request) {
		return authService.authWithKakao(request.code());
	}

	@PostMapping("google")
	@Override
	public SnsAuthResponseDto authByGoogle(@RequestBody AuthRequestDto request) {
		return authService.authWithGoogle(request.code());
	}

	@PostMapping("apple")
	@Override
	public SnsAuthResponseDto authByApple(@RequestBody AuthRequestDto request) {
		return authService.authWithAppleUser(request.code());
	}

	@GetMapping("kakao/callback")
	@Override
	public SnsAuthResponseDto redirectKakaoAuth(
		@RequestParam(name = "code") String authenticationCode
	) {
		return authService.authWithKakaoCode(authenticationCode);
	}

	@GetMapping("google/callback")
	@Override
	public SnsAuthResponseDto redirectGoogleAuth(
		@RequestParam(name = "code") String authenticationCode
	) {
		return authService.authWithGoogleCode(authenticationCode);
	}
}
