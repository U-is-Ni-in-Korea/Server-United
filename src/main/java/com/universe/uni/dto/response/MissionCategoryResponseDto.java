package com.universe.uni.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@JsonPropertyOrder({"id", "title", "description", "tip", "image", "missionContentList"})
@Builder
public record MissionCategoryResponseDto(
	Long id,
	String title,
	String description,
	String tip,
	String image,
	List<MissionContentDto> missionContentList
) {
}
