package com.universe.uni.domain.entity.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.universe.uni.domain.MatchType;

@Converter
public class MatchTypeAttributeConverter implements AttributeConverter<MatchType, String> {
	@Override
	public String convertToDatabaseColumn(MatchType attribute) {
		return attribute.name();
	}

	@Override
	public MatchType convertToEntityAttribute(String dbData) {
		return MatchType.findMatchTypeBy(dbData);
	}
}
