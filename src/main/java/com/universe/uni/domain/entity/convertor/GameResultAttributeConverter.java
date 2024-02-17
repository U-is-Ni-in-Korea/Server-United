package com.universe.uni.domain.entity.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.universe.uni.domain.GameResult;

@Converter
public class GameResultAttributeConverter implements AttributeConverter<GameResult, String> {

	@Override
	public String convertToDatabaseColumn(GameResult attribute) {
		return attribute.name();
	}

	@Override
	public GameResult convertToEntityAttribute(String dbData) {
		return GameResult.findMatchResultBy(dbData);
	}
}
