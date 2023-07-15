package com.universe.uni.domain;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomInviteCodeGenerator implements InviteCodeStrategy {

	public static final int MAX_CODE_LENGTH = 9;
	private final Random random;

	public RandomInviteCodeGenerator() throws NoSuchAlgorithmException {
		this.random = SecureRandom.getInstanceStrong();
	}

	@Override
	public String generate() {
		return createRandomString();
	}

	private String createRandomString() {
		StringBuilder randomBuf = new StringBuilder();
		for (int i = 0; i < MAX_CODE_LENGTH; i++) {
			if (random.nextBoolean()) {
				randomBuf.append((char)((random.nextInt(26)) + 97));
			} else {
				randomBuf.append(random.nextInt(10));
			}
		}
		return randomBuf.toString();
	}
}
