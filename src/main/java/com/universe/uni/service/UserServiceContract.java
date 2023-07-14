package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.UserDto;

public interface UserServiceContract {

	@Transactional
	UserDto findUser(Long userId);

	@Transactional
	Long findUserCoupleId(Long userId);

	@Transactional
	UserDto updateUserNickname(Long userId, String nickname);

	@Transactional
	UserDto updateUserNicknameAndImage(
		Long userId,
		String imageUrl,
		String nickname
	);
}
