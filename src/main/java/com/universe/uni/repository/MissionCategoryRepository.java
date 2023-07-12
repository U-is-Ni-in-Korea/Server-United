package com.universe.uni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universe.uni.domain.entity.MissionCategory;

public interface MissionCategoryRepository extends JpaRepository<MissionCategory, Long> {
}
