package com.universe.uni.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.universe.uni.external.request.GoogleAuthRequest;
import com.universe.uni.external.response.GoogleAccessTokenResponse;
import com.universe.uni.external.response.GoogleUserInfoResponse;

@FeignClient(value = "googleAuthClient", url = "${oauth.google.url.apis}")
public interface GoogleAuthClient {

	@PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	GoogleAccessTokenResponse getAccessToken(
		@RequestBody GoogleAuthRequest body
	);

	@GetMapping(value = "/tokeninfo", consumes = MediaType.APPLICATION_JSON_VALUE)
	GoogleUserInfoResponse getUserInfo(
		@RequestParam("id_token") String idToken
	);

}
