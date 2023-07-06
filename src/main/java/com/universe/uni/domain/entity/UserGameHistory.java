package com.universe.uni.domain.entity;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.universe.uni.domain.entity.convertor.GameResultAttributeConverter;
import com.universe.uni.domain.entity.convertor.GameTypeAttributeConverter;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.universe.uni.domain.GameResult;
import com.universe.uni.domain.GameType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_match_history")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserGameHistory {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_game_history_id")
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "game_id", nullable = false)
	private Long gameId;

	@Column(name = "result")
	@Convert(converter = GameResultAttributeConverter.class)
	private GameResult result;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "game_type")
	@Convert(converter = GameTypeAttributeConverter.class)
	private GameType gameType;

}
