package com.universe.uni.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인증 토큰 DTO")
public record AuthTokenDto(
        @JsonProperty("access_token")
        @Schema(description = "인증 토큰", nullable = false)
        String accessToken,
        @JsonProperty("refresh_token")
        @Schema(description = "인증 토큰이 만료 된 경우 갱신 토큰", nullable = true)
        String refreshToken
) {
}
