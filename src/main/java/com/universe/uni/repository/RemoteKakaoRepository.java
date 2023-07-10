package com.universe.uni.repository;

import org.springframework.stereotype.Repository;

import com.universe.uni.config.AuthConfig;
import com.universe.uni.external.KakaoAuthClient;
import com.universe.uni.external.request.KakaoAuthRequest;
import com.universe.uni.external.response.KakaoAuthResponse;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RemoteKakaoRepository implements KakaoRepository {
	private final AuthConfig authConfig;
	private final KakaoAuthClient kakaoAuthClient;

	@Override
	public KakaoAuthResponse fetchTokenBy(String code) {
		final KakaoAuthRequest request = KakaoAuthRequest.builder()
			.code(code)
			.grantType("authorization_code")
			.clientId(authConfig.getKakaoClientId())
			.clientSecret(authConfig.getKakaoClientSecret())
			.redirectUri(authConfig.getKakaoRedirectAuth())
			.build();
		return kakaoAuthClient.requestAuthToken(request);
	}
}
