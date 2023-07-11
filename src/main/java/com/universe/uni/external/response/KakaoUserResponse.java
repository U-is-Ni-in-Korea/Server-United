package com.universe.uni.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserResponse(
	@JsonProperty("id")
	Long id,
	@JsonProperty("kakao_account")
	KaKaoAccount kakaoAccount
) {
	public boolean isEmailNeedsAgreement() {
		if (kakaoAccount == null) {
			return false;
		}
		return (kakaoAccount.hasEmail && kakaoAccount.isEmailVerified && kakaoAccount.isEmailValid);
	}

	public record KaKaoAccount(
		@JsonProperty("has_email")
		boolean hasEmail,
		@JsonProperty("email_needs_agreement")
		boolean emailNeedsAgreement,
		@JsonProperty("is_email_valid")
		boolean isEmailValid,
		@JsonProperty("is_email_verified")
		boolean isEmailVerified,
		@JsonProperty("email")
		String email
	) {
	}
}
