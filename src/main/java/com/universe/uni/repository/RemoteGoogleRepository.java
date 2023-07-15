package com.universe.uni.repository;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.universe.uni.config.AuthConfig;
import com.universe.uni.external.GoogleAuthClient;
import com.universe.uni.external.request.GoogleAuthRequest;
import com.universe.uni.external.response.GoogleAccessTokenResponse;
import com.universe.uni.external.response.GoogleUserInfoResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RemoteGoogleRepository implements GoogleRepository {

	private final AuthConfig authConfig;
	private final GoogleAuthClient googleAuthClient;

	@Override
	public GoogleAccessTokenResponse fetchTokenBy(String code) {
		final GoogleAuthRequest request = GoogleAuthRequest.builder()
			.code(code)
			.grantType("authorization_code")
			.clientId(authConfig.getGoogleClientId())
			.clientSecret(authConfig.getGoogleClientSecret())
			.redirectUri(authConfig.getGoogleRedirectAuth())
			.build();
		try {
			log.info(new ObjectMapper().writeValueAsString(request));
		} catch (Exception exception) {
			log.info("object exception" + exception.getMessage());
		}
		return googleAuthClient.getAccessToken(request);
	}

	@Override
	public GoogleUserInfoResponse getUser(String idToken) {
		return googleAuthClient.getUserInfo(idToken);
	}
}
