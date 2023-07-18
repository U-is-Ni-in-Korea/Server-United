package com.universe.uni.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record UpdateUserProfileRequestDto(MultipartFile image, String nickname, String startDate) {
}
