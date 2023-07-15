package com.universe.uni.dto;

import java.time.LocalDateTime;

import com.universe.uni.domain.entity.ShortGame;

import lombok.Getter;

@Getter
public class ShortGameDto {
	private final Long id;
	private final Boolean enable;
	private final LocalDateTime finishAt;

	public ShortGameDto(ShortGame shortGame) {
		this.id = shortGame.getId();
		this.enable = shortGame.getEnable();
		this.finishAt = shortGame.getFinishAt();
	}

}
