package com.universe.uni.dto.request;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "게임 결과 입력 요청 DTO")
@Getter
public class EnterGameResultDto {

	@NotNull
	private Boolean result;
}
