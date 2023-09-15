package com.universe.uni.controller;

import com.universe.uni.controller.docs.WishCouponControllerContract;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.request.UpdateWishCouponRequestDto;
import com.universe.uni.dto.response.WishCouponResponseDto;
import com.universe.uni.service.WishCouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wish")
public class WishCouponController implements WishCouponControllerContract {

	private final WishCouponService wishCouponService;

	@PatchMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
	public void updateWishCoupon(@RequestBody UpdateWishCouponRequestDto requestDto) {
		wishCouponService.uploadWishCoupon(requestDto);
	}

	@PatchMapping("/{wishCouponId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
	public void useWishCoupon(@PathVariable Long wishCouponId) {
		wishCouponService.useWishCoupon(wishCouponId);
	}

	@GetMapping("/{wishCouponId}")
	@ResponseStatus(HttpStatus.OK)
	public WishCouponResponseDto getWishCoupon(@PathVariable Long wishCouponId) {
		return wishCouponService.getWishCoupon(wishCouponId);
	}
}
