package com.universe.uni.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.universe.uni.dto.WishCouponDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "소원권 상세 조회 결과 DTO")
@JsonPropertyOrder({"isMine", "nickname", "wishCoupon"})
@Builder
public record WishCouponResponseDto(
        @Schema(description = "내 소원권인지 아닌지 여부")
        boolean isMine,
        @Schema(description = "사용자 닉네임")
        String nickname,
        @Schema(description = "소원권 DTO", implementation = WishCouponDto.class)
        WishCouponDto wishCoupon
) {
}
