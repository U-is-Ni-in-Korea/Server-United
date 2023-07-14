package com.universe.uni.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.universe.uni.dto.UserDto;
import com.universe.uni.dto.request.UpdateUserNicknameRequestDto;
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

	@PatchMapping(value = "/info", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDto updateUserProfile(
		@AuthenticationPrincipal long userId,
		@RequestPart MultipartFile image,
		@RequestParam String nickname,
		@RequestParam String startDate
	) {
		final Long userCoupleId = userService.findUserCoupleId(userId);
		coupleService.updateCoupleStartDate(userCoupleId, startDate);
		final UserDto userDto = userService.updateUserNicknameAndImage(userId, "", nickname);
		return userDto;
	}
}
