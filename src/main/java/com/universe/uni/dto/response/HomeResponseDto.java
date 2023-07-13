package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.CoupleDto;
import com.universe.uni.dto.ShortGameDto;

import lombok.Builder;

@JsonPropertyOrder({"userId", "myScore", "partnerScore", "drawCount", "couple", "shortGame"})
@Builder
public record HomeResponseDto(
	Long userId,
	int myScore,
	int partnerScore,
	int drawCount,
	CoupleDto couple,
	ShortGameDto shortGame
) {
}
