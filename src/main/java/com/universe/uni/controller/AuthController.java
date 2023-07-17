package com.universe.uni.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.controller.docs.AuthControllerContract;
import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.dto.request.AuthRequestDto;
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
	public AuthTokenDto authByKakao(@RequestBody AuthRequestDto request) {
		return authService.authWithKakao(request.code());
	}

	@PostMapping("google")
	@Override
	public AuthTokenDto authByGoogle(@RequestBody AuthRequestDto request) {
		return authService.authWithGoogle(request.code());
	}

    @GetMapping("kakao/callback")
    public AuthRequestDto redirectKakaoAuth(@RequestParam(name = "code") String authenticationCode) {
        return new AuthRequestDto(authenticationCode);
    }

	@GetMapping("google/callback")
	public AuthRequestDto redirectGoogleAuth(@RequestParam(name = "code") String authenticationCode) {
		return new AuthRequestDto(authenticationCode);
	}
}
