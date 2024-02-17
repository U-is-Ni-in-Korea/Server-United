package com.universe.uni.domain;

public record AuthToken(
	String accessToken,
	String refreshToken
) {

}
