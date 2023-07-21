package com.universe.uni.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.entity.WishCoupon;
import com.universe.uni.dto.UserDto;
import com.universe.uni.dto.WishCouponDto;
import com.universe.uni.dto.response.ProfileResponseDto;
import com.universe.uni.dto.response.UserWishCouponResponseDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.mapper.UserMapper;
import com.universe.uni.repository.UserRepository;
import com.universe.uni.repository.WishCouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceContract {
	private final UserRepository userRepository;
	private final WishCouponRepository wishCouponRepository;
	private final UserUtil userUtil;
	private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	@Override
	public Long findUserCoupleId(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT))
			.getCouple()
			.getId();
	}

	@Override
	@Transactional
	public UserDto updateUserNickname(
		Long userId,
		String nickname
	) {
		final User user = userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT));
		user.updateNickname(nickname);
		return userMapper.toUserDto(user);
	}

	@Override
	@Transactional
	public UserDto updateUserNicknameAndImage(
		Long userId,
		String imageUrl,
		String nickName
	) {
		final User user = userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT));
		user.updateNickname(nickName);
		return userMapper.toUserDto(user);
	}

	@Override
	@Transactional
	public void withdrawalUser(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	@Transactional
	public UserWishCouponResponseDto getUserWishCouponList(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));

		List<WishCoupon> wishCouponList = wishCouponRepository.findByUserId(user.getId());

		int availableWishCoupon = (int)wishCouponList.stream().filter(wishCoupon -> !wishCoupon.isUsed()).count();

		int newWishCoupon = (int)wishCouponList.stream().filter(wishCoupon -> !wishCoupon.isVisible()).count();

		List<WishCouponDto> wishCouponDtoList = wishCouponList.stream()
			.sorted(Comparator.comparing(WishCoupon::getId).reversed())
			.collect(Collectors.partitioningBy(WishCoupon::isUsed))
			.entrySet()
			.stream()
			.flatMap(entry -> entry.getValue().stream())
			.map(this::fromWishCouponToWishCouponDto)
			.filter(wishCouponDto -> !wishCouponDto.content().isBlank())
			.collect(Collectors.toList());

		return UserWishCouponResponseDto.builder()
			.availableWishCoupon(availableWishCoupon)
			.newWishCoupon(newWishCoupon)
			.wishCouponList(wishCouponDtoList)
			.build();
	}

	@Override
	@Transactional
	public ProfileResponseDto getProfile() {
		User user = userUtil.getCurrentUser();
		return fromUserToProfileResponseDto(user);
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

	private ProfileResponseDto fromUserToProfileResponseDto(User user) {
		return ProfileResponseDto.builder()
			.userId(user.getId())
			.nickname(user.getNickname())
			.image(user.getImage())
			.startDate(user.getCouple().getStartDate().toString())
			.build();
	}
}
