package com.universe.uni.dto;

import java.time.LocalDateTime;

import com.universe.uni.domain.entity.ShortGame;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "단판 승부 정보 DTO")
@Getter
public class ShortGameDto {
	@Schema(description = "단판 승부 id")
	private final Long id;
	@Schema(description = "승부 진행 현황")
	private final Boolean enable;
	@Schema(description = "승부 끝난 시간", example = "yyyy-MM-dd HH:mm:ss.SSS")
	private final LocalDateTime finishAt;

	public ShortGameDto(ShortGame shortGame) {
		this.id = shortGame.getId();
		this.enable = shortGame.getEnable();
		this.finishAt = shortGame.getFinishAt();
	}

}
