package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.AuthTokenDto;

public interface AuthServiceContract {
    @Transactional
    AuthTokenDto authWithKakaoCode(String authenticationCode);

    @Transactional
	AuthTokenDto authWithKakao(String accessToken);

    @Transactional
    AuthTokenDto authWithGoogleCode(String authenticationCode);

    @Transactional
	AuthTokenDto authWithGoogle(String accessToken);

	@Transactional
	AuthTokenDto authWithAppleUser(String identityToken);

	@Transactional
	void unlinkSns(Long userId);
}
