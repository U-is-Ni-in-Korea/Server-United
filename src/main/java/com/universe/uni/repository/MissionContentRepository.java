package com.universe.uni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.MissionCategory;
import com.universe.uni.domain.entity.MissionContent;

public interface MissionContentRepository extends JpaRepository<MissionContent, Long> {

	List<MissionContent> findByMissionCategory(MissionCategory missionCategory);

	List<MissionContent> findByMissionCategoryId(Long missionCategoryId);
}
