package com.universe.uni.dto.response;

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
	private boolean isVisible;
	private boolean isUsed;
	private String usedAt;
	private String gameType;

	@Builder
	public UpdateWishCouponResponseDto(
		Long id,
		String image,
		String content,
		boolean isVisible,
		boolean isUsed,
		String usedAt,
		String gameType
	) {
		this.id = id;
		this.image = image;
		this.content = content;
		this.isVisible = isVisible;
		this.isUsed = isUsed;
		this.usedAt = usedAt;
		this.gameType = gameType;
	}
}
