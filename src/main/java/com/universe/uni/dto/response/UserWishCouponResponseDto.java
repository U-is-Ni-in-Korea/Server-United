package com.universe.uni.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.WishCouponDto;

import lombok.Builder;

@JsonPropertyOrder({"availableWishCoupon", "newWishCoupon", "wishCouponList"})
@Builder
public record UserWishCouponResponseDto(
	int availableWishCoupon,
	int newWishCoupon,
	List<WishCouponDto> wishCouponList
) {

}
