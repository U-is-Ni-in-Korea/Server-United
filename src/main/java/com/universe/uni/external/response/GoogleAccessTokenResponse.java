package com.universe.uni.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleAccessTokenResponse(
	@JsonProperty("id_token")
	String idToken
) {
}
