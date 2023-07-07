package com.universe.uni.domain;

import java.util.Arrays;
import java.util.Objects;

public enum SnsType {
    KAKAO,
    GOOGLE,
    APPLE;

    public static SnsType findSnsTypeBy(String snsTypeName) {
        return Arrays.stream(values())
                .filter(snsType -> Objects.equals(snsType.name(), snsTypeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported social login type: " + snsTypeName));
    }
}
