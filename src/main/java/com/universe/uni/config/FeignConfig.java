package com.universe.uni.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@EnableFeignClients(basePackages = "com.universe.uni.external")
@RequiredArgsConstructor
@Configuration
public class FeignConfig {
}
