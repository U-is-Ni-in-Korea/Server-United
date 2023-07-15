package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.CoupleDto;
import com.universe.uni.dto.ShortGameDto;

import lombok.Builder;

@JsonPropertyOrder({"userId", "roundGameId", "myScore", "partnerScore", "drawCount", "dDay", "couple", "shortGame"})
@Builder
public record HomeResponseDto(
	Long userId,
	Long roundGameId,
	int myScore,
	int partnerScore,
	int drawCount,
	int dDay,
	CoupleDto couple,
	ShortGameDto shortGame
) {
}
