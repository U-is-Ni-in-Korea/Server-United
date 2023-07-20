package com.universe.uni.domain;

import java.util.Base64;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.universe.uni.exception.UnauthorizedException;
import com.universe.uni.exception.dto.ErrorType;

@Component
public class AppleTokenDecodeManager implements AppleTokenManager {

	@Override
	public String decodeEmail(String token) {
		String[] encodedToken = token.split("\\.");
		String encodedPayload = encodedToken[1];
		Base64.Decoder decoder = Base64.getDecoder();
		String payLoad = new String(decoder.decode(encodedPayload));

		ObjectMapper mapper = new ObjectMapper();
		ApplePayload applePayload = null;
		try {
			applePayload = mapper.readValue(payLoad, ApplePayload.class);
		} catch (JsonProcessingException e) {
			throw new UnauthorizedException(ErrorType.UNSUPPORTED_TOKEN);
		}
		return applePayload.email();
	}
}
