package com.universe.uni.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "커플 생성을 위한 DTO")
public record CreateCoupleRequestDto(
	@Schema(description = "YYYY-MM-DD 와 같은 커플 시작일") String startDate
) {

}
