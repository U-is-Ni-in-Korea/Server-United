package com.universe.uni.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.WishCoupon;
import com.universe.uni.dto.WishCouponDto;
import com.universe.uni.dto.response.UserWishCouponResponseDto;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.repository.UserRepository;
import com.universe.uni.repository.WishCouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final WishCouponRepository wishCouponRepository;

	public UserWishCouponResponseDto getUserWishCouponList(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));

		List<WishCoupon> wishCouponList = wishCouponRepository.findByUserId(user.getId());

		int availableWishCoupon = (int)wishCouponList.stream().filter(wishCoupon -> !wishCoupon.isUsed()).count();

		int newWishCoupon = (int)wishCouponList.stream().filter(WishCoupon::isVisible).count();

		List<WishCouponDto> wishCouponDtoList = wishCouponList.stream()
			.map(this::fromWishCouponToWishCouponDto)
			.toList();

		return UserWishCouponResponseDto.builder()
			.availableWishCoupon(availableWishCoupon)
			.newWishCoupon(newWishCoupon)
			.wishCouponList(wishCouponDtoList)
			.build();
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
}
