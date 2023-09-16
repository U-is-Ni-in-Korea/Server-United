package com.universe.uni.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "소원권 수정 or 생성 요청 DTO")
public record UpdateWishCouponRequestDto(
        @Schema(description = "무조건 “SHORT”로 보내주세요 → 아니면 500뜸!!")
        String gameType,
        @Schema(description = "소원 내용")
        String content
) {
}
