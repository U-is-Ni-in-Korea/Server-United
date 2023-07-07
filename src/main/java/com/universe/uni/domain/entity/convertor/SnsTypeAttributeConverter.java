package com.universe.uni.domain.entity.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.universe.uni.domain.SnsType;

@Converter
public final class SnsTypeAttributeConverter implements AttributeConverter<SnsType, String> {
	@Override
	public String convertToDatabaseColumn(SnsType attribute) {
		return attribute.name();
	}

	@Override
	public SnsType convertToEntityAttribute(String dbData) {
		return SnsType.findSnsTypeBy(dbData);
	}
}
