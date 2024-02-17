package com.universe.uni.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Profile("!prod")
@OpenAPIDefinition(
	servers = {
		@Server(url = "http://uni-sparkle.kro.kr", description = "개발 배포 주소"),
		@Server(url = "http://localhost:8080", description = "로컬 환경 주소")
	},
	info = @Info(
		title = "Sparkle API",
		version = "v1",
		description = "sparkle API 입니다"
	),
	security = @SecurityRequirement(name = "Authorization")
)
@SecurityScheme(
	name = "Authorization",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "Bearer",
	in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {

}
