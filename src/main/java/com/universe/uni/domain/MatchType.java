package com.universe.uni.domain;

import java.util.Arrays;
import java.util.Objects;

public enum MatchType {
	SHORT,
	LONG;

	public static MatchType findMatchTypeBy(String matchTypeName) {
		return Arrays.stream(values())
				.filter(matchType -> Objects.equals(matchType.name(), matchTypeName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unsupported match type" + matchTypeName));
	}
}
