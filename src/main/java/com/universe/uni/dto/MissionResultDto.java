package com.universe.uni.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@JsonPropertyOrder({"content", "result", "time"})
@Builder
public record MissionResultDto(
	String content,
	String result,
	String time
) {
}
