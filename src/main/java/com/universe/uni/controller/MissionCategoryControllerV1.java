package com.universe.uni.controller;

import com.universe.uni.controller.docs.MissionControllerV1Contract;
import com.universe.uni.dto.response.MissionCategoryWithContentsDto;
import com.universe.uni.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
