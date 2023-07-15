package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.response.CoupleDto;

public interface CoupleServiceContract {

	CoupleDto createCoupleByStartDate(
		Long userId,
		String startDate
	);

	CoupleDto findCouple(Long coupleId);

	@Transactional
	void joinCouple(Long userId, String inviteCode);

	@Transactional
	CoupleDto updateCoupleStartDate(Long coupleId, String startDate);
}
