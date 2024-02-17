package com.universe.uni.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.controller.docs.MissionControllerV1Contract;
import com.universe.uni.dto.response.MissionCategoryWithContentsDto;
import com.universe.uni.service.MissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mission")
public class MissionCategoryControllerV1 implements MissionControllerV1Contract {

	private final MissionService missionService;

	@GetMapping("/{missionCategoryId}")
	@ResponseStatus(HttpStatus.OK)
	@Override
	public MissionCategoryWithContentsDto getSelectedMissionCategory(@PathVariable Long missionCategoryId) {
		return missionService.getSelectedMissionCategory(missionCategoryId);
	}

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	@Override
	public List<MissionCategoryWithContentsDto> getAllMissionCategories() {
		return missionService.getMissionCategories();
	}
}
