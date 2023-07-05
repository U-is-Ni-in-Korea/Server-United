package com.universe.uni.domain.entity.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.universe.uni.domain.MatchResult;

@Converter
public class MatchResultAttributeConverter implements AttributeConverter<MatchResult, String> {
	@Override
	public String convertToDatabaseColumn(MatchResult attribute) {
		return attribute.name();
	}

	@Override
	public MatchResult convertToEntityAttribute(String dbData) {
		return MatchResult.findMatchResultBy(dbData);
	}
}
