package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@JsonPropertyOrder({"id", "content"})
@Builder
public record MissionContentDto(
	Long id,
	String content
) {
}
