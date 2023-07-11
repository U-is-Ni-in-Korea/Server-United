package com.universe.uni.service;

import org.springframework.stereotype.Service;

import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.repository.KakaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceContract {

	private final JwtManager jwtManager;
	private final KakaoRepository kakaoRepository;

	public AuthTokenDto authWithKakao(String code) {

	}
}
