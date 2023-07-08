package com.universe.uni.domain.entity.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.universe.uni.domain.MissionType;

@Converter
public class MissionTypeAttributeConverter implements AttributeConverter<MissionType, String> {
	@Override
	public String convertToDatabaseColumn(MissionType attribute) {
		return attribute.name();
	}

	@Override
	public MissionType convertToEntityAttribute(String dbData) {
		return MissionType.findMissionTypeBy(dbData);
	}
}
