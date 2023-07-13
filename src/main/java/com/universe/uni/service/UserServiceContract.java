package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.UserDto;

public interface UserServiceContract {

	@Transactional
	UserDto findUser(Long userId);

	@Transactional
	UserDto updateUser(Long userId, String nickname);
}
