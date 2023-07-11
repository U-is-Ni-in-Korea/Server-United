package com.universe.uni.domain;

import java.util.Arrays;
import java.util.Objects;

public enum GameType {
	SHORT,
	LONG;

	public static GameType findMatchTypeBy(String gameTypeName) {
		return Arrays.stream(values())
			.filter(gameType -> Objects.equals(gameType.name(), gameTypeName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Unsupported match type" + gameTypeName));
	}
}
