package com.universe.uni.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@JsonPropertyOrder({"result", "time"})
@Builder
public record MissionResultDto(
	String result,
	String time
) {
}
