package com.universe.uni.service;

import static com.universe.uni.exception.dto.ErrorType.*;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.universe.uni.domain.entity.MissionCategory;
import com.universe.uni.domain.entity.MissionContent;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.repository.MissionCategoryRepository;
import com.universe.uni.repository.MissionContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionService {

	private final MissionCategoryRepository missionCategoryRepository;
	private final MissionContentRepository missionContentRepository;

	public MissionCategory getMissionCategoryById(Long missionCategoryId) {
		return missionCategoryRepository.findById(missionCategoryId)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_MISSION_CATEGORY_EXCEPTION));
	}

	public MissionContent getMissionContentByRandom(MissionCategory missionCategory) {
		List<MissionContent> missionContentList = missionContentRepository
			.findByMissionCategory(missionCategory);

		try {
			int randomIndex = new Random().nextInt(missionContentList.size());
			return missionContentList.get(randomIndex);
		} catch (Exception exception) {
			throw new NotFoundException(NOT_FOUND_MISSION_CONTENT);
		}
	}
}
