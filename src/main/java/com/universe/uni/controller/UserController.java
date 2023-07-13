package com.universe.uni.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.universe.uni.dto.UserDto;
import com.universe.uni.service.UserServiceContract;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserServiceContract userService;

	@PatchMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserDto updateUserProfile(
		@AuthenticationPrincipal long userId,
		@RequestPart("image") MultipartFile imageFile,
		@RequestParam String nickname
	) {
		return userService.updateUser(userId, nickname);
	}
}
