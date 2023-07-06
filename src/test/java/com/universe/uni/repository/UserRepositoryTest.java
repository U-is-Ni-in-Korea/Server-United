package com.universe.uni.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.universe.uni.domain.SnsType;
import com.universe.uni.domain.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

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
}
