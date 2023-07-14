package com.universe.uni.service;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.universe.uni.domain.entity.User;
import com.universe.uni.dto.UserDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.mapper.UserMapper;
import com.universe.uni.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceContract {
	private final UserRepository userRepository;
	private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	@Override
	@Transactional
	public UserDto findUser(Long userId) {
		final User user = findUserBy(userId);
		return userMapper.toUserDto(user);
	}

	@Override
	@Transactional
	public Long findUserCoupleId(Long userId) {
		return findUserBy(userId).getCouple().getId();
	}

	@Override
	@Transactional
	public UserDto updateUserNickname(
		Long userId,
		String nickname
	) {
		final User user = findUserBy(userId);
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
		final User user = findUserBy(userId);
		user.updateNickname(nickName);
		return userMapper.toUserDto(user);
	}

	private User findUserBy(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT));
	}
}
