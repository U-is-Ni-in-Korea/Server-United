package com.universe.uni.repository;

import com.universe.uni.external.response.KakaoAuthResponse;
import com.universe.uni.external.response.KakaoUnlinkResponse;
import com.universe.uni.external.response.KakaoUserResponse;

public interface KakaoRepository {
	KakaoAuthResponse fetchTokenBy(String code);

	KakaoUserResponse getUser(String accessToken);

	KakaoUnlinkResponse unlinkUser(Long targetId);
}
