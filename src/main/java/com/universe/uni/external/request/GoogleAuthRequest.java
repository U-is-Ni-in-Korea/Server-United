package com.universe.uni.external.request;

import feign.form.FormProperty;
import lombok.Builder;

@Builder
public record GoogleAuthRequest(
	@FormProperty("code")
	String code,
	@FormProperty("grant_type")
	String grantType,
	@FormProperty("client_id")
	String clientId,
	@FormProperty("client_secret")
	String clientSecret,
	@FormProperty("redirect_uri")
	String redirectUri
) {
}
