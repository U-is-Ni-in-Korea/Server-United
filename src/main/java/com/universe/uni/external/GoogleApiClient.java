package com.universe.uni.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.universe.uni.external.response.GoogleAccessTokenResponse;

@FeignClient(value = "googleApiClient", url = "${oauth.google.url.tokeninfo}")
public interface GoogleApiClient {

	@PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	GoogleAccessTokenResponse getAccessToken(
		@RequestBody GoogleApiClient body
	);
}
