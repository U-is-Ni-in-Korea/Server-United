package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.AuthTokenDto;

public interface AuthServiceContract {
	@Transactional
	AuthTokenDto authWithKakao(String accessToken);

	@Transactional
	AuthTokenDto authWithGoogle(String accessToken);

	@Transactional
	AuthTokenDto authWithAppleUser(String identityToken);
}
