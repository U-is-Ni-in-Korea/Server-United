package com.universe.uni.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleUserInfoResponse(
	@JsonProperty("alg")
	String mAlg,
	@JsonProperty("at_hash")
	String mAtHash,
	@JsonProperty("aud")
	String mAud,
	@JsonProperty("email")
	String mEmail,
	@JsonProperty("email_verified")
	String mEmailVerified,
	@JsonProperty("exp")
	String mExp,
	@JsonProperty("family_name")
	String mFamilyName,
	@JsonProperty("given_name")
	String mGivenName,
	@JsonProperty("iat")
	String mIat,
	@JsonProperty("iss")
	String mIss,
	@JsonProperty("kid")
	String mKid,
	@JsonProperty("locale")
	String mLocale,
	@JsonProperty("name")
	String mName,
	@JsonProperty("picture")
	String mPicture,
	@JsonProperty("sub")
	String mSub,
	@JsonProperty("typ")
	String mTyp
) {
}
