package com.universe.uni.dto.response;

import com.universe.uni.domain.entity.MissionContent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "라운드 미션 정보 DTO")
@Getter
public class RoundMissionContentDto {

	@Schema(description = "라운드 미션 정보 id")
	private final Long id;
	@Schema(description = "미션 카테고리 정보")
	private final MissionCategoryDto missionCategory;
	private final String content;
	@Schema(description = "미션 추천 시간")
	private final String recommendTime;

	public RoundMissionContentDto(MissionContent missionContent) {
		this.id = missionContent.getId();
		this.missionCategory = new MissionCategoryDto(missionContent.getMissionCategory());
		this.content = missionContent.getContent();
		this.recommendTime = missionContent.getRecommendTime();
	}
}