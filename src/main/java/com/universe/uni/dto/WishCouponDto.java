package com.universe.uni.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "소원권 정보 DTO")
@JsonPropertyOrder({"id", "image", "content", "isVisible", "isUsed", "usedAt", "gameType"})
@Builder
public record WishCouponDto(
	@Schema(description = "소원권 id")
	Long id,
	@Schema(description = "소원권 이미지")
	String image,
	@Schema(description = "소원 내용")
	String content,
	@Schema(description = "소원권 내용이 있는지 없는지 여부")
	@JsonProperty("isVisible") boolean visible,
	@Schema(description = "소원권 사용여부")
	@JsonProperty("isUsed") boolean used,
	@Schema(description = "소원권 사용날짜")
	String usedAt,
	@Schema(description = "원데이면 SHORT, 무제한이면 LONG")
	String gameType
) {

}
