package com.universe.uni.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.request.UpdateWishCouponRequestDto;
import com.universe.uni.dto.response.UpdateWishCouponResponseDto;
import com.universe.uni.service.WishCouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wish")
public class WishCouponController {

	private final WishCouponService wishCouponService;

	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public UpdateWishCouponResponseDto updateWishCoupon(
		@RequestBody UpdateWishCouponRequestDto requestDto
	) {
		return wishCouponService.uploadWishCoupon(requestDto);
	}
}
