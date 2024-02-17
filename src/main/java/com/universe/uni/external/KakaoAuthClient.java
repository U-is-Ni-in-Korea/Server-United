package com.universe.uni.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.universe.uni.external.request.KakaoAuthRequest;
import com.universe.uni.external.response.KakaoAuthResponse;

@FeignClient(value = "kakaoAuthClient", url = "${oauth.kakao.url.auth}")
public interface KakaoAuthClient {

	@PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	KakaoAuthResponse requestAuthToken(
		@RequestBody KakaoAuthRequest body
	);
}
