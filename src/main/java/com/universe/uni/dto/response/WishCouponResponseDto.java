package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@JsonPropertyOrder({"id", "isMine", "image", "content", "isVisible", "isUsed", "usedAt", "gameType"})
@Builder
public record WishCouponResponseDto(
	Long id,
	boolean isMine,
	String image,
	String content,
	@JsonProperty("isVisible") boolean visible,
	@JsonProperty("isUsed") boolean used,
	String usedAt,
	String gameType
) {
}
