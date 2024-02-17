package com.universe.uni.external.request;

import feign.form.FormProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class KakaoAuthRequest {

	@FormProperty("code")
	private String code;
	@FormProperty("grant_type")
	private String grantType;
	@FormProperty("client_id")
	private String clientId;
	@FormProperty("client_secret")
	private String clientSecret;
	@FormProperty("redirect_uri")
	private String redirectUri;
}
