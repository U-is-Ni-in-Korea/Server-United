package com.universe.uni.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.entity.convertor.GameResultAttributeConverter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "round_mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoundMission {

	@Id
	@Column(name = "round_mission_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "round_game_id", nullable = false)
	private RoundGame roundGame;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mission_content_id", nullable = false)
	private MissionContent missionContent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "result")
	@Convert(converter = GameResultAttributeConverter.class)
	private GameResult result;

	@Column(name = "final_result")
	@Convert(converter = GameResultAttributeConverter.class)
	private GameResult finalResult;
}
