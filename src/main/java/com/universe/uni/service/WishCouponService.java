package com.universe.uni.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.WishCoupon;
import com.universe.uni.dto.request.UpdateWishCouponRequestDto;
import com.universe.uni.dto.response.UpdateWishCouponResponseDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.repository.WishCouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WishCouponService {

	private final WishCouponRepository wishCouponRepository;

	@Transactional
	public UpdateWishCouponResponseDto uploadWishCoupon(
		UpdateWishCouponRequestDto requestDto
	) {
		GameType gameType = GameType.valueOf(requestDto.getGameType());
		List<WishCoupon> wishCouponList = wishCouponRepository
			.findByGameTypeAndIsVisibleFalseAndIsUsedFalseAndUsedAtIsNull(gameType);

		if (wishCouponList.isEmpty()) {
			throw new BadRequestException(ErrorType.INVALID_REQUEST_METHOD);
		}

		WishCoupon wishCoupon = wishCouponList.get(0);

		wishCoupon.setContent(requestDto.getContent());
		wishCoupon.setVisible(true);

		return fromWishCoupon(wishCoupon);
	}

	private UpdateWishCouponResponseDto fromWishCoupon(WishCoupon wishCoupon) {
		String usedAt = wishCoupon.getUsedAt() != null ? wishCoupon.getUsedAt().toString() : null;

		return UpdateWishCouponResponseDto.builder()
			.id(wishCoupon.getId())
			.image(wishCoupon.getImage())
			.content(wishCoupon.getContent())
			.isVisible(wishCoupon.isVisible())
			.isUsed(wishCoupon.isUsed())
			.usedAt(usedAt)
			.gameType(String.valueOf(wishCoupon.getGameType()))
			.build();
	}
}
