package com.universe.uni.domain;

import java.util.Arrays;
import java.util.Objects;

public enum MatchResult {
	WIN, LOSE, DRAW;

	public static MatchResult findMatchResultBy(String matchResultName) {
		return Arrays.stream(values())
				.filter(matchResult -> Objects.equals(matchResult.name(),matchResultName))
				.findFirst()
				.orElseThrow(()->new IllegalArgumentException("Unsupported match result type"));
	}
}
