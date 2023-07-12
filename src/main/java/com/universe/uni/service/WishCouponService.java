package com.universe.uni.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.WishCoupon;
import com.universe.uni.dto.request.UpdateWishCouponRequestDto;
import com.universe.uni.dto.response.UpdateWishCouponResponseDto;
import com.universe.uni.dto.response.WishCouponResponseDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.repository.WishCouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WishCouponService {

	private final WishCouponRepository wishCouponRepository;

	@Transactional
	public UpdateWishCouponResponseDto uploadWishCoupon(UpdateWishCouponRequestDto requestDto) {
		GameType gameType = GameType.valueOf(requestDto.gameType());
		List<WishCoupon> wishCouponList = wishCouponRepository.findByGameTypeAndIsVisibleFalseAndIsUsedFalseAndUsedAtIsNull(
			gameType);

		if (wishCouponList.isEmpty()) {
			throw new BadRequestException(ErrorType.INVALID_REQUEST_METHOD);
		}

		WishCoupon wishCoupon = wishCouponList.get(0);

		wishCoupon.updateContent(requestDto.content());
		wishCoupon.makeVisible();

		return fromWishCouponToUpdateWishCouponResponseDto(wishCoupon);
	}

	@Transactional
	public WishCouponResponseDto getWishCoupon(Long wishCouponId) {
		WishCoupon wishCoupon = wishCouponRepository.findById(wishCouponId)
			.orElseThrow(() -> new NotFoundException(ErrorType.INVALID_ENDPOINT_EXCEPTION));
		return fromWishCouponToWishCouponResponseDto(wishCoupon);
	}

	private UpdateWishCouponResponseDto fromWishCouponToUpdateWishCouponResponseDto(WishCoupon wishCoupon) {
		String usedAt = wishCoupon.getUsedAt() != null ? wishCoupon.getUsedAt().toString() : null;

		return UpdateWishCouponResponseDto.builder()
			.id(wishCoupon.getId())
			.image(wishCoupon.getImage())
			.content(wishCoupon.getContent())
			.visible(wishCoupon.isVisible())
			.used(wishCoupon.isUsed())
			.usedAt(usedAt)
			.gameType(String.valueOf(wishCoupon.getGameType()))
			.build();
	}

	private WishCouponResponseDto fromWishCouponToWishCouponResponseDto(WishCoupon wishCoupon) {
		/** TODO 영주 : 추후 1L 내 userId로 바꾸기*/
		boolean isMine = wishCoupon.getUser().getId() == 1L;
		String usedAt = wishCoupon.getUsedAt() != null ? wishCoupon.getUsedAt().toString() : null;

		return WishCouponResponseDto.builder()
			.id(wishCoupon.getId())
			.isMine(isMine)
			.image(wishCoupon.getImage())
			.content(wishCoupon.getContent())
			.visible(wishCoupon.isVisible())
			.used(wishCoupon.isUsed())
			.usedAt(usedAt)
			.gameType(String.valueOf(wishCoupon.getGameType()))
			.build();
	}
}
