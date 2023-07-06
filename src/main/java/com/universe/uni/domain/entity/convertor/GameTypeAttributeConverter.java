package com.universe.uni.domain.entity.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.universe.uni.domain.GameType;

@Converter
public class GameTypeAttributeConverter implements AttributeConverter<GameType, String> {
	@Override
	public String convertToDatabaseColumn(GameType attribute) {
		return attribute.name();
	}

	@Override
	public GameType convertToEntityAttribute(String dbData) {
		return GameType.findMatchTypeBy(dbData);
	}
}
