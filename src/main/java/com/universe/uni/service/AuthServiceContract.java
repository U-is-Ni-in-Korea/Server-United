package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.AuthTokenDto;

public interface AuthServiceContract {
	@Transactional
	AuthTokenDto authWithKakao(String code);

	@Transactional
	AuthTokenDto authWithGoogle(String code);
}
