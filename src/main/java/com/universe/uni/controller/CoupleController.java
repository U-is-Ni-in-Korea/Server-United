package com.universe.uni.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.request.CreateCoupleRequestDto;
import com.universe.uni.dto.request.JoinCoupleRequestDto;
import com.universe.uni.dto.response.CoupleConnectionResponseDto;
import com.universe.uni.dto.response.CoupleDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.service.CoupleServiceContract;
import com.universe.uni.service.UserServiceContract;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/couple")
public class CoupleController {

	private final UserServiceContract userService;
	private final CoupleServiceContract coupleService;

	@PostMapping("")
	public CoupleDto createCoupleBy(
		@AuthenticationPrincipal Long userId,
		@RequestBody CreateCoupleRequestDto request
	) {
		try {
			final Long coupleId = userService.findUserCoupleId(userId);
			return coupleService.findCouple(coupleId);
		} catch (NullPointerException exception) {
			return coupleService.createCoupleByStartDate(userId, request.startDate());
		}
	}

	@PostMapping("join")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void joinCouple(
		@AuthenticationPrincipal Long userId,
		@RequestBody JoinCoupleRequestDto body
	) {
		coupleService.joinCouple(userId, body.inviteCode());
	}

	@GetMapping("join")
	@ResponseStatus(HttpStatus.OK)
	public CoupleConnectionResponseDto checkConnection(
		@AuthenticationPrincipal Long userId
	) {
		return coupleService.checkConnection(userId);
	}
}
