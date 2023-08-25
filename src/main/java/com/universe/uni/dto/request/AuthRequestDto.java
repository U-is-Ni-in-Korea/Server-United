package com.universe.uni.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인증 코드 발급을 위한 Request DTO")
public record AuthRequestDto(
        @Schema(description = "각 인증 수단으로 받은 인증 코드")
		String code
) {
}
