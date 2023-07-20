package com.universe.uni.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApplePayload(
	@JsonProperty("aud")
	String aud,
	@JsonProperty("auth_time")
	Long authTime,
	@JsonProperty("c_hash")
	String cHash,
	@JsonProperty("email")
	String email,
	@JsonProperty("email_verified")
	String emailVerified,
	@JsonProperty("exp")
	Long exp,
	@JsonProperty("iat")
	Long iat,
	@JsonProperty("is_private_email")
	String isPrivateEmail,
	@JsonProperty("iss")
	String iss,
	@JsonProperty("nonce_supported")
	Boolean nonceSupported,
	@JsonProperty("sub")
	String sub
) {
}
