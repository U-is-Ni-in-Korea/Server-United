package com.universe.uni.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@JsonPropertyOrder({"id", "content", "recommend_time"})
@Schema(description = "미션 내용을 담는 DTO")
@Builder
public record MissionContentDto(
	@Schema(description = "미션 내용 id")
	Long id,
	@Schema(description = "미션 내용")
	String content,
	@Schema(description = "미션 추천 시간")
	@JsonProperty("recommend_time")
	String recommendTime
) {

}
