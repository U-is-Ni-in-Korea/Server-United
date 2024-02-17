package com.universe.uni.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@JsonPropertyOrder({"id", "startDate", "heartToken"})
@Builder
public record CoupleDto(
	Long id,
	String startDate,
	int heartToken
) {

}
