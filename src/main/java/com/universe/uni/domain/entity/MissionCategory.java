package com.universe.uni.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.universe.uni.domain.MissionType;
import com.universe.uni.domain.entity.convertor.GameResultAttributeConverter;
import com.universe.uni.domain.entity.convertor.MissionTypeAttributeConverter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mission_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mission_category_id", nullable = false)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "tip", nullable = false)
	private String tip;

	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "level", nullable = false)
	private int level;

	@Column(name = "expected_time", nullable = false)
	private int expectedTime;

	@Column(name = "mission_type")
	@Convert(converter = MissionTypeAttributeConverter.class)
	private MissionType missionType;
}