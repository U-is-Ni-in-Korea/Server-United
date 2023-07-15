package com.universe.uni.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.universe.uni.domain.SnsType;
import com.universe.uni.domain.entity.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@ParameterizedTest
	@EnumSource(SnsType.class)
	@DisplayName("유저는 SNS 정보를 사용하여 생성되어야 한다.")
	void givenSnsInformation_whenUseSns_thenCreateUser(SnsType expectedSnsType) {
		// given
		String expectedSnsAuthToken = "test token";
		User expectedUser = User.builder()
			.snsType(expectedSnsType)
			.snsAuthCode(expectedSnsAuthToken)
			.build();
		// when
		User actualUser = userRepository.save(expectedUser);
		// then
		Assertions.assertAll(
			() -> {
				assertThat(actualUser.getId()).isNotNull();
			},
			() -> {
				assertThat(actualUser.getSnsType()).isEqualTo(expectedSnsType);
			},
			() -> {
				assertThat(actualUser.getSnsAuthCode()).isEqualTo(expectedSnsAuthToken);
			}
		);
	}

	@Test
	@DisplayName("유저 정보에 닉네임을 추가 할 수 있다.")
	void givenUser_whenAddNickName_thenUserHasNickname() {
		// given
		String expectedNickname = "test";
		User expectedUser = User.builder()
			.snsType(SnsType.GOOGLE)
			.snsAuthCode("test")
			.build();
		User actualUser = userRepository.save(expectedUser);
		// when
		actualUser.updateNickname(expectedNickname);
		// then
		assertThat(userRepository.findById(actualUser.getId()).get().getNickname()).isEqualTo(expectedNickname);
	}

	@Test
	@DisplayName("유저 정보에 10자가 넘는 닉네임을 추가 할 수 없다.")
	void givenUser_whenAddOverLengthNickName_thenException() {
		// given
		String expectedNickname = "12345678901";
		User expectedUser = User.builder()
			.snsType(SnsType.GOOGLE)
			.snsAuthCode("test")
			.build();
		User actualUser = userRepository.save(expectedUser);
		// when
		// then
		assertThatThrownBy(() -> actualUser.updateNickname(expectedNickname))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("nickname exceeds the maximum length of 10 characters");
	}
}
