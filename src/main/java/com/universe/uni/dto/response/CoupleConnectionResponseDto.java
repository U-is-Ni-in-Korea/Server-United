package com.universe.uni.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "커플 연결 확인 DTO")
public record CoupleConnectionResponseDto(
	@Schema(description = "connection 여부")
	Boolean connection
) {

}
