package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.universe.uni.domain.AuthToken;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인증 토큰 및 couple 정보 DTO")
public record SnsAuthResponseDto(
	@JsonProperty("access_token")
	@Schema(description = "인증 토큰")
	String accessToken,
	@JsonProperty("refresh_token")
	@Schema(description = "인증 토큰이 만료 된 경우 갱신 토큰", nullable = true)
	String refreshToken,
	@JsonProperty("couple_id")
	@Schema(description = "커플 연결정보 커플이 없다면 null", nullable = true)
	Long coupleId
) {

	public static SnsAuthResponseDto of(AuthToken authToken, Long coupleId) {
		return new SnsAuthResponseDto(
			authToken.accessToken(),
			authToken.refreshToken(),
			coupleId
		);
	}
}
