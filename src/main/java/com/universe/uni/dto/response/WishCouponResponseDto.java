package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.WishCouponDto;

import lombok.Builder;

@JsonPropertyOrder({"isMine", "nickname", "wishCoupon"})
@Builder
public record WishCouponResponseDto(
	boolean isMine,
	String nickname,
	WishCouponDto wishCoupon
) {
}
