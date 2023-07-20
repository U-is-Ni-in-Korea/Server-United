package com.universe.uni.domain;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AppleTokenManager {

	String decodeEmail(String token);
}
