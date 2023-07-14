package com.universe.uni.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.universe.uni.domain.entity.Couple;
import com.universe.uni.dto.response.CoupleDto;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.mapper.CoupleMapper;
import com.universe.uni.repository.CoupleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoupleService implements CoupleServiceContract {
	private final CoupleMapper coupleMapper = Mappers.getMapper(CoupleMapper.class);
	private final CoupleRepository coupleRepository;

	@Override
	@Transactional
	public CoupleDto updateCoupleStartDate(Long coupleId, String startDate) {
		final LocalDate date = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
		final Couple couple = findCoupleBy(coupleId);
		couple.updateStartDate(date);
		return coupleMapper.toCoupleDto(couple);
	}

	private Couple findCoupleBy(Long coupleId) {
		return coupleRepository.findById(coupleId)
			.orElseThrow(() -> new BadRequestException(ErrorType.COUPLE_NOT_EXISTENT));
	}
}
