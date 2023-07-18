package com.universe.uni.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.UserDto;
import com.universe.uni.dto.request.UpdateUserNicknameRequestDto;
import com.universe.uni.dto.request.UpdateUserProfileRequestDto;
import com.universe.uni.dto.response.ProfileResponseDto;
import com.universe.uni.dto.response.UserWishCouponResponseDto;
import com.universe.uni.service.CoupleServiceContract;
import com.universe.uni.service.UserServiceContract;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserServiceContract userService;
	private final CoupleServiceContract coupleService;

	@PatchMapping("")
	@ResponseStatus(HttpStatus.OK)
	public UserDto updateUserNickname(
		@AuthenticationPrincipal long userId,
		@RequestBody UpdateUserNicknameRequestDto requestDto
	) {
		return userService.updateUserNickname(userId, requestDto.nickname());
	}

	@PatchMapping(
		value = "/info",
		consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDto updateUserProfile(
		@AuthenticationPrincipal long userId,
		@ModelAttribute final UpdateUserProfileRequestDto updateUserProfileRequestDto
	) {
		final Long userCoupleId = userService.findUserCoupleId(userId);
		coupleService.updateCoupleStartDate(userCoupleId, updateUserProfileRequestDto.startDate());
		return userService.updateUserNicknameAndImage(userId, "", updateUserProfileRequestDto.nickname());
	}

	@GetMapping("/{userId}/wish")
	@ResponseStatus(HttpStatus.OK)
	public UserWishCouponResponseDto getUserWishCouponList(@PathVariable Long userId) {
		return userService.getUserWishCouponList(userId);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ProfileResponseDto getProfile() {
		return userService.getProfile();
	}
}
