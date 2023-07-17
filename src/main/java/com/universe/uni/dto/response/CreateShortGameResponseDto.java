package com.universe.uni.dto.response;

import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.domain.entity.ShortGame;
import com.universe.uni.dto.RoundMissionDto;
import com.universe.uni.dto.ShortGameDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateShortGameResponseDto {
	private ShortGameDto shortGame;
	private Long roundGameId;
	private RoundMissionDto roundMission;

	public static CreateShortGameResponseDto of(
		ShortGame shortGame,
		Long roundGameId,
		RoundMission roundMission)
	{
		return new CreateShortGameResponseDto(
			new ShortGameDto(shortGame),
			roundGameId,
			new RoundMissionDto(roundMission));
	}
}
