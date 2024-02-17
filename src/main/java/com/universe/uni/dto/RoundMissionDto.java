package com.universe.uni.dto;

import java.time.LocalDateTime;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.entity.RoundMission;
import com.universe.uni.dto.response.RoundMissionContentDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "라운드 미션 정보 DTO")
@Getter
public class RoundMissionDto {

	@Schema(description = "라운드 미션 id")
	private final long id;
	@Schema(description = "라운드 미션 정보")
	private final RoundMissionContentDto missionContent;
	@Schema(description = "승부 결과")
	private final GameResult result;
	@Schema(description = "최종 승부 결과")
	private final GameResult finalResult;
	@Schema(description = "미션 실패 OR 완료 누른 시간", example = "yyyy-MM-dd HH:mm:ss.SSS")
	private final LocalDateTime updatedAt;

	public RoundMissionDto(RoundMission roundMission) {
		this.id = roundMission.getId();
		this.missionContent = new RoundMissionContentDto(roundMission.getMissionContent());
		this.result = roundMission.getResult();
		this.finalResult = roundMission.getFinalResult();
		this.updatedAt = roundMission.getUpdatedAt();
	}
}
