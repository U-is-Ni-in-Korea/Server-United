package com.universe.uni.mapper;

import org.mapstruct.Mapper;

import com.universe.uni.domain.entity.User;
import com.universe.uni.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDto toUserDto(User user);
}
