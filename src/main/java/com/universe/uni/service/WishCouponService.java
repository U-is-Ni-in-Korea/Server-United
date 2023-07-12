package com.universe.uni.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.Game;
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

		return fromWishCoupon(wishCoupon);
	}

	private UpdateWishCouponResponseDto fromWishCoupon(WishCoupon wishCoupon) {
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

	public WishCoupon issueWishCoupon(String content, Game game) {
		if(content == null || content.isEmpty()) {
			return createWishCoupon(content, game, false);
		} else {
			return createWishCoupon(content, game, true);
		}
	}

	private WishCoupon createWishCoupon(String content, Game game, Boolean isVisible) {
		return WishCoupon.builder()
			.content(content)
			.isVisible(isVisible)
			.game(game)
			.gameType(GameType.SHORT)
			.build();
	}

	@Transactional
	public void saveWishCoupon(WishCoupon wishCoupon) {
		wishCouponRepository.save(wishCoupon);
	}
}
