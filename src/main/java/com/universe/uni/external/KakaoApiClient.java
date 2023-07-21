package com.universe.uni.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.universe.uni.external.request.KakaoUnlinkRequest;
import com.universe.uni.external.response.KakaoUnlinkResponse;
import com.universe.uni.external.response.KakaoUserResponse;

@FeignClient(value = "kakaoApiClient", url = "${oauth.kakao.url.api}")
public interface KakaoApiClient {
	@GetMapping(value = "/v2/user/me", consumes = MediaType.APPLICATION_JSON_VALUE)
	KakaoUserResponse getUser(@RequestHeader("Authorization") String token);

	@PostMapping(value = "/v1/user/unlink", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	KakaoUnlinkResponse postUnlinkUser(
		@RequestHeader("Authorization") String adminKey,
		@RequestBody KakaoUnlinkRequest request
	);
}
