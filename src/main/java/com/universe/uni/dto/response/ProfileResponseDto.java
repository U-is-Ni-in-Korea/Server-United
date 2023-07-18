package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@JsonPropertyOrder({"userId", "nickname", "image", "startDate"})
@Builder
public record ProfileResponseDto(
	Long userId,
	String nickname,
	String image,
	String startDate
) {
}