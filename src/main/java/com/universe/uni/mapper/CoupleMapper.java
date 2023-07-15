package com.universe.uni.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.universe.uni.domain.entity.Couple;
import com.universe.uni.dto.response.CoupleDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CoupleMapper {

	CoupleDto toCoupleDto(Couple couple);
}
