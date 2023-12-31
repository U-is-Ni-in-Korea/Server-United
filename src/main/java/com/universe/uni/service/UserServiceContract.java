package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.UserDto;
import com.universe.uni.dto.response.ProfileResponseDto;
import com.universe.uni.dto.response.UserWishCouponResponseDto;

public interface UserServiceContract {

	Long findUserCoupleId(Long userId);

	@Transactional
	UserDto updateUserNickname(Long userId, String nickname);

	@Transactional
	UserDto updateUserNicknameAndImage(
		Long userId,
		String imageUrl,
		String nickname
	);

	@Transactional
	void withdrawalUser(Long userId);

	@Transactional
	UserWishCouponResponseDto getUserWishCouponList(Long userId);

	@Transactional
	ProfileResponseDto getProfile();
}
