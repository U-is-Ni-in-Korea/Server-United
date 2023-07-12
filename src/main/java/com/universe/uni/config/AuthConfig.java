package com.universe.uni.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class AuthConfig {
	@Value("${oauth.kakao.client.id}")
	private String kakaoClientId;

	@Value("${oauth.kakao.client.secret}")
	private String kakaoClientSecret;

	@Value("${oauth.kakao.redirect.auth}")
	private String kakaoRedirectAuth;

	@Value("${oauth.google.client.id}")
	private String googleClientId;

	@Value("${oauth.google.client.secret}")
	private String googleClientSecret;

	@Value("${oauth.google.redirect.auth}")
	private String googleRedirectAuth;
}
