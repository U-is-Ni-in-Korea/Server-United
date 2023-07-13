package com.universe.uni.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.convertor.GameResultAttributeConverter;
import com.universe.uni.domain.entity.convertor.GameTypeAttributeConverter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "user_game_history")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserGameHistory {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_game_history_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

	@Column(name = "result")
	@Convert(converter = GameResultAttributeConverter.class)
	private GameResult result;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "game_type")
	@Convert(converter = GameTypeAttributeConverter.class)
	private GameType gameType;

	@Builder
	public UserGameHistory(User user, Game game, GameResult gameResult, LocalDateTime now, GameType gameType) {
		this.user = user;
		this.game = game;
		this.result = gameResult;
		this.updatedAt = now;
		this.gameType = gameType;
	}
}
