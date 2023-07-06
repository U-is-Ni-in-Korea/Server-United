package com.universe.uni.domain.entity;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.universe.uni.domain.entity.convertor.GameResultAttributeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.universe.uni.domain.GameResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "round_history")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RoundHistory {

	@Id
	@Column(name = "round_history_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "round_game_id", nullable = false)
	private Long roundGameId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "result")
	@Convert(converter = GameResultAttributeConverter.class)
	private GameResult result;

	@Column(name = "user_match_history_id", nullable = false)
	private Long userMatchHistoryId;
}
