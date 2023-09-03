package com.universe.uni.service;

import static com.universe.uni.exception.dto.ErrorType.NOT_FOUND_WISH_COUPON;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.Game;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.WishCoupon;
import com.universe.uni.dto.WishCouponDto;
import com.universe.uni.dto.request.UpdateWishCouponRequestDto;
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
	private final UserUtil userUtil;

	public void uploadWishCoupon(UpdateWishCouponRequestDto requestDto) {
		User user = userUtil.getCurrentUser();
		GameType gameType = GameType.valueOf(requestDto.gameType());
		List<WishCoupon> wishCouponList = wishCouponRepository.findByUserAndGameTypeAndIsVisibleFalseAndIsUsedFalseAndUsedAtIsNull(
			user, gameType);

		if (wishCouponList.isEmpty()) {
			throw new BadRequestException(ErrorType.INVALID_REQUEST_METHOD);
		}

		WishCoupon wishCoupon = wishCouponList.get(0);

		String normalizedContent = WhitespaceNormalizer.normalizeWhitespace(requestDto.content());

		wishCoupon.updateContent(normalizedContent);
		wishCoupon.makeVisible();
	}

	public void useWishCoupon(Long wishCouponId) {
		WishCoupon wishCoupon = wishCouponRepository.findById(wishCouponId)
			.orElseThrow(() -> new NotFoundException(ErrorType.INVALID_ENDPOINT_EXCEPTION));

		wishCoupon.useWishCoupon();
	}

	public WishCouponResponseDto getWishCoupon(Long wishCouponId) {
		WishCoupon wishCoupon = wishCouponRepository.findById(wishCouponId)
			.orElseThrow(() -> new NotFoundException(ErrorType.INVALID_ENDPOINT_EXCEPTION));

		User user = userUtil.getCurrentUser();

		boolean isMine = Objects.equals(wishCoupon.getUser().getId(), user.getId());

		return WishCouponResponseDto.builder()
			.isMine(isMine)
			.nickname(wishCoupon.getUser().getNickname())
			.wishCoupon(fromWishCouponToWishCouponDto(wishCoupon))
			.build();
	}

	public WishCoupon issueWishCoupon(String content, Game game) {
		if (content == null || content.isEmpty()) {
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
			.image(getRandomImage())
			.build();
	}

	@Transactional
	public void saveWishCoupon(WishCoupon wishCoupon) {
		wishCouponRepository.save(wishCoupon);
	}

	private WishCouponDto fromWishCouponToWishCouponDto(WishCoupon wishCoupon) {
		String usedAt = wishCoupon.getUsedAt() != null ? wishCoupon.getUsedAt().toString() : null;

		return WishCouponDto.builder()
			.id(wishCoupon.getId())
			.image(wishCoupon.getImage())
			.content(wishCoupon.getContent())
			.visible(wishCoupon.isVisible())
			.used(wishCoupon.isUsed())
			.usedAt(usedAt)
			.gameType(String.valueOf(wishCoupon.getGameType()))
			.build();
	}

	@Transactional
	public void giveWishCouponToWinner(Game game, User user) {
		WishCoupon wishCoupon = getWishCouponByGame(game);
		wishCoupon.setWinnerToWishCoupon(user);
	}

	private WishCoupon getWishCouponByGame(Game game) {
		return wishCouponRepository.findByGame(game).orElseThrow(() -> new NotFoundException(NOT_FOUND_WISH_COUPON));
	}

	private String getRandomImage() {
		List<String> imageList = List.of(
			"https://uni-sparkle.s3.ap-northeast-2.amazonaws.com/category/wish1.png",
			"https://uni-sparkle.s3.ap-northeast-2.amazonaws.com/category/wish2.png",
			"https://uni-sparkle.s3.ap-northeast-2.amazonaws.com/category/wish3.png");

		int randomImageIdx = new Random().nextInt(imageList.size());
		return imageList.get(randomImageIdx);
	}
}
