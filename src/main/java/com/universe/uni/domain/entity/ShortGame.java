package com.universe.uni.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("ShortGame")
@PrimaryKeyJoinColumn(name = "game_id")
public class ShortGame extends Game {

	@Builder
	public ShortGame(Couple couple) {
		super(couple);
	}
}
