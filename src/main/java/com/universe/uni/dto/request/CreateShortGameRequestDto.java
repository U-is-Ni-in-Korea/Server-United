package com.universe.uni.dto.request;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "단기 승부 요청 DTO")
@Getter
public class CreateShortGameRequestDto {

	@Schema(description = "사용자가 선택한 미션 카테고리 id")
	@NotNull
	private Long missionCategoryId;

	@Schema(description = "사용자가 작성한 소원권 내용", nullable = true)
	@NotNull
	private String wishContent;
}
