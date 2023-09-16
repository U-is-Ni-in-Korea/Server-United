package com.universe.uni.dto.response;

import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.domain.entity.ShortGame;
import com.universe.uni.dto.RoundMissionDto;
import com.universe.uni.dto.ShortGameDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "단판 승부 생성 결과 DTO")
@Getter
@AllArgsConstructor
public class CreateShortGameResponseDto {
	@Schema(description = "단판 승부 정보")
	private ShortGameDto shortGame;
	@Schema(description = "라운드 게임 아이디")
	private Long roundGameId;
	@Schema(description = "사용자 미션 정보")
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
