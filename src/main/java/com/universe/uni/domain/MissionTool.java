package com.universe.uni.domain;

import java.util.Arrays;
import java.util.Objects;

public enum MissionTool {
	MEMO,
	TIMER,
	NONE;

	public static MissionTool findMissionToolBy(String missionTypeName) {
		return Arrays.stream(values())
			.filter(missionType -> Objects.equals(missionType.name(), missionTypeName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Unsupported mission type - " + missionTypeName));
	}
}
