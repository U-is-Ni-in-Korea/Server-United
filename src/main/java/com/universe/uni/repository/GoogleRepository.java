package com.universe.uni.repository;

import com.universe.uni.external.response.GoogleAccessTokenResponse;
import com.universe.uni.external.response.GoogleUserInfoResponse;

public interface GoogleRepository {
	GoogleAccessTokenResponse fetchTokenBy(String code);

	GoogleUserInfoResponse getUser(String idToken);
}
