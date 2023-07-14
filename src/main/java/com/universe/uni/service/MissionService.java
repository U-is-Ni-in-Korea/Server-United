package com.universe.uni.service;

import static com.universe.uni.exception.dto.ErrorType.NOT_FOUND_MISSION_CATEGORY_EXCEPTION;
import static com.universe.uni.exception.dto.ErrorType.NOT_FOUND_MISSION_CONTENT;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universe.uni.domain.entity.MissionCategory;
import com.universe.uni.domain.entity.MissionContent;
import com.universe.uni.dto.MissionContentDto;
import com.universe.uni.dto.response.MissionCategoryResponseDto;
import com.universe.uni.exception.NotFoundException;
import com.universe.uni.repository.MissionCategoryRepository;
import com.universe.uni.repository.MissionContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

	private final MissionCategoryRepository missionCategoryRepository;
	private final MissionContentRepository missionContentRepository;

	public MissionCategory getMissionCategoryById(Long missionCategoryId) {
		return missionCategoryRepository.findById(missionCategoryId)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_MISSION_CATEGORY_EXCEPTION));
	}

	public MissionContent getMissionContentByRandom(MissionCategory missionCategory) {
		List<MissionContent> missionContentList = missionContentRepository.findByMissionCategory(missionCategory);

		try {
			int randomIndex = new Random().nextInt(missionContentList.size());
			return missionContentList.get(randomIndex);
		} catch (Exception exception) {
			throw new NotFoundException(NOT_FOUND_MISSION_CONTENT);
		}
	}

	public MissionCategoryResponseDto getMissionCategory(Long missionCategoryId) {
		MissionCategory missionCategory = missionCategoryRepository.findById(missionCategoryId)
			.orElseThrow(() -> new NotFoundException(NOT_FOUND_MISSION_CATEGORY_EXCEPTION));
		return fromMissionCategoryToMissionCategoryResponseDto(missionCategory);
	}

	public List<MissionCategoryResponseDto> getMissionCategoryList() {
		List<MissionCategory> missionCategoryList = missionCategoryRepository.findAll();
		return missionCategoryList
			.stream()
			.map(this::fromMissionCategoryToMissionCategoryResponseDto)
			.toList();
	}

	private MissionCategoryResponseDto fromMissionCategoryToMissionCategoryResponseDto(
		MissionCategory missionCategory) {

		List<MissionContent> missionContentList = missionContentRepository.findByMissionCategoryId(
			missionCategory.getId());

		List<MissionContentDto> missionContentDtoList = missionContentList.stream()
			.map(this::fromMissionContentToMissionContentResponseDto)
			.collect(Collectors.toList());

		return MissionCategoryResponseDto.builder()
			.id(missionCategory.getId())
			.title(missionCategory.getTitle())
			.description(missionCategory.getDescription())
			.tip(missionCategory.getTip())
			.image(missionCategory.getImage())
			.missionContentList(missionContentDtoList)
			.build();
	}

	private MissionContentDto fromMissionContentToMissionContentResponseDto(MissionContent missionContent) {
		return MissionContentDto.builder()
			.id(missionContent.getId())
			.content(missionContent.getContent())
			.build();
	}

}
