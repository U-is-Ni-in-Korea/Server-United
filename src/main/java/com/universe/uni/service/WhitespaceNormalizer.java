package com.universe.uni.service;

import org.springframework.stereotype.Component;

@Component
public class WhitespaceNormalizer {

	public static String normalizeWhitespace(String input) {
		if (input == null) {
			return null;
		}
		return input.replaceAll("\\s+", " ");
	}
}
