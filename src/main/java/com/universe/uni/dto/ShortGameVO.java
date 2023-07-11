package com.universe.uni.dto;

import java.time.LocalDateTime;

import com.universe.uni.domain.entity.ShortGame;

import lombok.Getter;

@Getter
public class ShortGameVO {
	private Long id;
	private Boolean enable;
	private LocalDateTime finishAt;

	public ShortGameVO(ShortGame shortGame) {
		this.id = shortGame.getId();
		this.enable = shortGame.getEnable();
		this.finishAt = shortGame.getFinishAt();
	}

}
