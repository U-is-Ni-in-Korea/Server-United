package com.universe.uni.domain;

import java.util.Arrays;
import java.util.Objects;

public enum MissionType {
	SAME,
	DIFFERENCE;

	public static MissionType findMissionTypeBy(String missionTypeName) {
		return Arrays.stream(values())
			.filter(missionType -> Objects.equals(missionType.name(), missionTypeName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Unsupported social login type: " + missionTypeName));
	}
}
