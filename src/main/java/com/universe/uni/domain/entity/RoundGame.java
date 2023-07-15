package com.universe.uni.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "round_game")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoundGame {

	@Id
	@Column(name = "round_game_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mission_category_id", nullable = false)
	private MissionCategory missionCategory;

	@Column(name = "enable", nullable = false)
	private Boolean enable;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_game_history_id")
	private UserGameHistory userGameHistory;

	@Builder
	public RoundGame(Game game, MissionCategory missionCategory) {
		this.game = game;
		this.missionCategory = missionCategory;
		this.enable = Boolean.TRUE;
	}

	public void finishGame() {
		this.enable = false;
	}
}
