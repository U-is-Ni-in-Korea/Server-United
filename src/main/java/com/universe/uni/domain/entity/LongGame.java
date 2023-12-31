package com.universe.uni.domain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("LongGame")
@PrimaryKeyJoinColumn(name = "game_id")
public class LongGame extends Game {

	@Column(name = "duration", nullable = false)
	private int duration;

	@Column(name = "round", nullable = false)
	private int round;

	@Column(name = "rest_of_game", nullable = false)
	private int restOfGame;
}
