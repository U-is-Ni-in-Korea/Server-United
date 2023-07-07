package com.universe.uni.domain.entity;

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
import com.universe.uni.domain.entity.convertor.GameResultAttributeConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "round_history")
@NoArgsConstructor(access = PROTECTED)
public class RoundHistory {

	@Id
	@Column(name = "round_history_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "round_game_id", nullable = false)
	private RoundGame roundGame;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "result")
	@Convert(converter = GameResultAttributeConverter.class)
	private GameResult result;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_game_history_id", nullable = false)
	private UserGameHistory userGameHistory;
}
