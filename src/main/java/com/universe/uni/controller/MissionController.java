package com.universe.uni.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universe.uni.dto.response.MissionCategoryResponseDto;
import com.universe.uni.service.MissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission")
public class MissionController {
	private final MissionService missionService;

	@GetMapping("/{missionCategoryId}")
	@ResponseStatus(HttpStatus.OK)
	public MissionCategoryResponseDto getMissionCategory(@PathVariable Long missionCategoryId) {
		return missionService.getMissionCategory(missionCategoryId);
	}
}
