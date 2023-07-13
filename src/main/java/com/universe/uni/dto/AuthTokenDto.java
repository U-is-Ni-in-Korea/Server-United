package com.universe.uni.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthTokenDto(
	@JsonProperty("access_token") String accessToken,
	@JsonProperty("refresh_token") String refreshToken
) {
}
