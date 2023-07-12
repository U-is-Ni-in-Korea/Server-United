package com.universe.uni.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.response.UserWishCouponResponseDto;
import com.universe.uni.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/{userId}/wish")
	@ResponseStatus(HttpStatus.OK)
	public UserWishCouponResponseDto getUserWishCouponList(@PathVariable Long userId) {
		return userService.getUserWishCouponList(userId);
	}
}
