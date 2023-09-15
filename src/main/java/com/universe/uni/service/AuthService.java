package com.universe.uni.service;

import javax.transaction.Transactional;

import com.universe.uni.dto.response.SnsAuthResponseDto;
import com.universe.uni.external.response.GoogleAccessTokenResponse;
import com.universe.uni.external.response.KakaoAuthResponse;

import org.springframework.stereotype.Service;

import com.universe.uni.domain.AppleTokenManager;
import com.universe.uni.domain.SnsType;
import com.universe.uni.domain.entity.User;
import com.universe.uni.domain.AuthToken;
import com.universe.uni.exception.BadRequestException;
import com.universe.uni.exception.dto.ErrorType;
import com.universe.uni.external.response.GoogleUserInfoResponse;
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
	private final AppleTokenManager appleTokenManager;

    @Override
    @Transactional
    public SnsAuthResponseDto authWithKakaoCode(String authenticationCode) {
        final KakaoAuthResponse kakaoAuth = kakaoRepository.fetchTokenBy(authenticationCode);
        return authWithKakao(kakaoAuth.accessToken());
    }

	@Override
	@Transactional
	public SnsAuthResponseDto authWithKakao(String accessToken) {
		final KakaoUserResponse kakaoUser = kakaoRepository.getUser(accessToken);
		final User user = userRepository.findBySnsAuthCode(kakaoUser.id().toString())
			.orElseGet(() -> userRepository.save(registerKakaoUser(kakaoUser)));
		return beIssuedAuthToken(user);
	}

	private User registerKakaoUser(KakaoUserResponse kakaoUser) {
		return User.builder()
			.snsType(SnsType.KAKAO)
			.snsAuthCode(kakaoUser.id().toString())
			.build();
	}

    @Override
    @Transactional
    public SnsAuthResponseDto authWithGoogleCode(String authenticationCode) {
        final GoogleAccessTokenResponse googleAuth = googleRepository.fetchTokenBy(authenticationCode);
        return authWithGoogle(googleAuth.idToken());
    }

	@Override
	@Transactional
	public SnsAuthResponseDto authWithGoogle(String accessToken) {
		final GoogleUserInfoResponse googleUser = googleRepository.getUser(accessToken);
		final User user = userRepository.findBySnsAuthCode(googleUser.mSub())
			.orElseGet(() -> userRepository.save(registerGoogleUser(googleUser)));
		return beIssuedAuthToken(user);
	}

	private User registerGoogleUser(GoogleUserInfoResponse googleUser) {
		return User.builder()
			.snsType(SnsType.GOOGLE)
			.snsAuthCode(googleUser.mSub())
			.build();
	}

	@Override
	@Transactional
	public SnsAuthResponseDto authWithAppleUser(String identityToken) {
		final String userEmail = appleTokenManager.decodeEmail(identityToken);
		final User user = userRepository.findBySnsAuthCode(userEmail)
			.orElseGet(() -> userRepository.save(registerAppleUser(userEmail)));
		return beIssuedAuthToken(user);
	}

	private User registerAppleUser(String email) {
		return User.builder()
			.snsType(SnsType.APPLE)
			.snsAuthCode(email)
			.build();
	}

	private SnsAuthResponseDto beIssuedAuthToken(User user) {
        final AuthToken authToken = jwtManager.issueToken(user.getId());
        if (Objects.isNull(user.getCouple())) {
            return SnsAuthResponseDto.of(authToken, null);
        }
        return SnsAuthResponseDto.of(authToken, user.getCouple().getId());
	}

	@Override
	@Transactional
	public void unlinkSns(Long userId) {
		final User user = userRepository.findById(userId)
			.orElseThrow(() -> new BadRequestException(ErrorType.USER_NOT_EXISTENT));
		if (user.getSnsType() == SnsType.KAKAO) {
			kakaoRepository.unlinkUser(Long.valueOf(user.getSnsAuthCode()));
		}
	}
}
