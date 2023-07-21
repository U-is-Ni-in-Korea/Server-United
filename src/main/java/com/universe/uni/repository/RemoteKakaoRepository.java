package com.universe.uni.repository;

import org.springframework.stereotype.Repository;

import com.universe.uni.config.AuthConfig;
import com.universe.uni.external.KakaoApiClient;
import com.universe.uni.external.KakaoAuthClient;
import com.universe.uni.external.request.KakaoAuthRequest;
import com.universe.uni.external.request.KakaoUnlinkRequest;
import com.universe.uni.external.response.KakaoAuthResponse;
import com.universe.uni.external.response.KakaoUnlinkResponse;
import com.universe.uni.external.response.KakaoUserResponse;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RemoteKakaoRepository implements KakaoRepository {

	private final AuthConfig authConfig;
	private final KakaoAuthClient kakaoAuthClient;
	private final KakaoApiClient kakaoApiClient;

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

	@Override
	public KakaoUserResponse getUser(String accessToken) {
		final String token = "Bearer " + accessToken;
		return kakaoApiClient.getUser(token);
	}

	@Override
	public KakaoUnlinkResponse unlinkUser(Long targetId) {
		final String token = "KakaoAK " + authConfig.getKakaoClientAdmin();
		final KakaoUnlinkRequest request = new KakaoUnlinkRequest("user_id", targetId);
		return kakaoApiClient.postUnlinkUser(
			token,
			request
		);
	}
}
