package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.response.CoupleDto;

public interface CoupleServiceContract {
	@Transactional
	CoupleDto updateCoupleStartDate(Long coupleId, String startDate);
}
