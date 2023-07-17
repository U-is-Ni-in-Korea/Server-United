package com.universe.uni.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.universe.uni.domain.SnsType;
import com.universe.uni.domain.entity.User;
import com.universe.uni.dto.AuthTokenDto;
import com.universe.uni.external.response.GoogleAccessTokenResponse;
import com.universe.uni.external.response.GoogleUserInfoResponse;
import com.universe.uni.external.response.KakaoAuthResponse;
import com.universe.uni.external.response.KakaoUserResponse;
import com.universe.uni.repository.GoogleRepository;
import com.universe.uni.repository.KakaoRepository;
import com.universe.uni.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceContract {

	private final JwtManager jwtManager;
	private final KakaoRepository kakaoRepository;
	private final GoogleRepository googleRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public AuthTokenDto authWithKakao(String accessToken) {
		final KakaoUserResponse kakaoUser = kakaoRepository.getUser(accessToken);
		final User user = userRepository.findBySnsAuthCode(kakaoUser.id().toString())
			.orElseGet(() -> userRepository.save(registerKakaoUser(kakaoUser)));
		return beIssuedAuthToken(user.getId());
	}

	private User registerKakaoUser(KakaoUserResponse kakaoUser) {
		return User.builder()
			.snsType(SnsType.KAKAO)
			.snsAuthCode(kakaoUser.id().toString())
			.build();
	}

	@Override
	@Transactional
	public AuthTokenDto authWithGoogle(String accessToken) {
		final GoogleUserInfoResponse googleUser = googleRepository.getUser(accessToken);
		final User user = userRepository.findBySnsAuthCode(googleUser.mSub())
			.orElseGet(() -> userRepository.save(registerGoogleUser(googleUser)));
		return beIssuedAuthToken(user.getId());
	}

	private User registerGoogleUser(GoogleUserInfoResponse googleUser) {
		return User.builder()
			.snsType(SnsType.GOOGLE)
			.snsAuthCode(googleUser.mSub())
			.build();
	}

	private AuthTokenDto beIssuedAuthToken(long userId) {
		return jwtManager.issueToken(userId);
	}
}
