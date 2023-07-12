package com.universe.uni.dto.response;

import java.time.LocalDateTime;

import com.universe.uni.domain.entity.ShortGame;

import lombok.Getter;

@Getter
public class ShortGameDto {
	private Long id;
	private Boolean enable;
	private LocalDateTime finishAt;

	public ShortGameDto(ShortGame shortGame) {
		this.id = shortGame.getId();
		this.enable = shortGame.getEnable();
		this.finishAt = shortGame.getFinishAt();
	}

}
