package com.universe.uni.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "커플 등록시 초대 코드 입력 DTO")
public record JoinCoupleRequestDto(
	@Schema(description = "커플 생성시 발급 된 커플 초대 코드") String inviteCode
) {
}
