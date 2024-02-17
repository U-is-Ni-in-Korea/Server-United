package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.response.SnsAuthResponseDto;

public interface AuthServiceContract {

	@Transactional
	SnsAuthResponseDto authWithKakaoCode(String authenticationCode);

	@Transactional
	SnsAuthResponseDto authWithKakao(String accessToken);

	@Transactional
	SnsAuthResponseDto authWithGoogleCode(String authenticationCode);

	@Transactional
	SnsAuthResponseDto authWithGoogle(String accessToken);

	@Transactional
	SnsAuthResponseDto authWithAppleUser(String identityToken);

	@Transactional
	void unlinkSns(Long userId);
}
