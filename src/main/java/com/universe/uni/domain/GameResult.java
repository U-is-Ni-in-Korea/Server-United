package com.universe.uni.domain;

import java.util.Arrays;
import java.util.Objects;

public enum GameResult {
	WIN, LOSE, DRAW, UNDECIDED;

	public static GameResult findMatchResultBy(String gameResultName) {
		return Arrays.stream(values())
				.filter(gameResult -> Objects.equals(gameResult.name(),gameResultName))
				.findFirst()
				.orElseThrow(()->new IllegalArgumentException("Unsupported match result type"));
	}
}
