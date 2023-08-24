package com.universe.uni.domain.entity.convertor;

import com.universe.uni.domain.MissionTool;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MissionToolAttributeConverter implements AttributeConverter<MissionTool, String> {
    @Override
    public String convertToDatabaseColumn(MissionTool attribute) {
        return attribute.name();
    }

    @Override
    public MissionTool convertToEntityAttribute(String dbData) {
        return MissionTool.findMissionToolBy(dbData);
    }
}
