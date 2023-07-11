package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateWishCouponResponseDto {
	private Long id;
	private String image;
	private String content;
	@JsonProperty("isVisible")
	private boolean visible;
	@JsonProperty("isUsed")
	private boolean used;
	private String usedAt;
	private String gameType;

	@Builder
	public UpdateWishCouponResponseDto(
		Long id,
		String image,
		String content,
		boolean visible,
		boolean used,
		String usedAt,
		String gameType
	) {
		this.id = id;
		this.image = image;
		this.content = content;
		this.visible = visible;
		this.used = used;
		this.usedAt = usedAt;
		this.gameType = gameType;
	}
}
