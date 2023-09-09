package com.universe.uni.service;

import static com.universe.uni.exception.dto.ErrorType.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.universe.uni.domain.InviteCodeStrategy;
import com.universe.uni.domain.entity.Couple;
import com.universe.uni.domain.entity.User;
import com.universe.uni.dto.response.CoupleConnectionResponseDto;
import com.universe.uni.dto.response.CoupleDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.ConflictException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.mapper.CoupleMapper;
import com.universe.uni.repository.CoupleRepository;
import com.universe.uni.repository.UserRepository;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoupleService implements CoupleServiceContract {

	private final CoupleRepository coupleRepository;
	private final UserRepository userRepository;
	private final InviteCodeStrategy inviteCodeStrategy;
	private final CoupleMapper coupleMapper = Mappers.getMapper(CoupleMapper.class);

	@Override
	@Transactional
	public CoupleDto createCoupleByStartDate(
		Long userId,
		String startDate
	) {
		final LocalDate localDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
		final String inviteCode = inviteCodeStrategy.generate();
		final Couple couple = Couple.builder()
			.startDate(localDate)
			.inviteCode(inviteCode)
			.build();
		final User user = userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT));
		user.connectCouple(couple);
		return coupleMapper.toCoupleDto(coupleRepository.save(couple));
	}

	@Override
	public CoupleDto findCouple(Long coupleId) {
		final Couple couple = coupleRepository.findById(coupleId)
			.orElseThrow(() -> new BadRequestException(ErrorType.COUPLE_NOT_EXISTENT));
		return coupleMapper.toCoupleDto(couple);
	}

	@Override
	@Transactional
	public void joinCouple(Long userId, String inviteCode) {
		final Couple couple = coupleRepository.findByInviteCode(inviteCode)
			.orElseThrow(() -> new BadRequestException(ErrorType.INVALID_INVITE_CODE));
		validateCoupleConnected(couple);
		final User user = userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT));
		user.connectCouple(couple);

	}

	private void validateCoupleConnected(Couple couple) {
		if(userRepository.countByCoupleId(couple.getId()) >= 2) {
			throw new ConflictException(COUPLE_ALREADY_CONNECTED);
		}
	}

	@Override
	@Transactional
	public CoupleDto updateCoupleStartDate(Long coupleId, String startDate) {
		final LocalDate date = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
		final Couple couple = coupleRepository.findById(coupleId)
			.orElseThrow(() -> new BadRequestException(ErrorType.COUPLE_NOT_EXISTENT));
		couple.updateStartDate(date);
		return coupleMapper.toCoupleDto(couple);
	}

	@Override
	@Transactional
	public CoupleConnectionResponseDto checkConnection(Long userId) {
		final User user = userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT));
		final Couple couple = user.getCouple();
		List<User> userList = userRepository.findByCouple(couple);
		if(userList.size() == 2) {
			return new CoupleConnectionResponseDto(true);
		}
		return new CoupleConnectionResponseDto(false);
	}

	@Override
	@Transactional
	public void deleteCouple(Long coupleId) {
		coupleRepository.deleteById(coupleId);
	}

	@Override
	@Transactional
	public void disconnectCouple(Long userId) {
		final User user = userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT));
		final Couple couple = user.getCouple();
		deleteCouple(couple.getId());
	}
}
