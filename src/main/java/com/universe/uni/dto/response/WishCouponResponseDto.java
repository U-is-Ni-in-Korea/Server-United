package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.WishCouponDto;

import lombok.Builder;

@JsonPropertyOrder({"isMine", "wishCoupon"})
@Builder
public record WishCouponResponseDto(
	boolean isMine,
	WishCouponDto wishCoupon
) {
}
