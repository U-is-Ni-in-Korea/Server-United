package com.universe.uni.dto;

import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.domain.entity.ShortGame;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateShortGameResponseDto {
	private ShortGameVO shortGame;
	private RoundMissionVO roundMission;

	public static CreateShortGameResponseDto of(ShortGame shortGame, RoundMission roundMission) {
		return new CreateShortGameResponseDto(new ShortGameVO(shortGame), new RoundMissionVO(roundMission));
	}
}
