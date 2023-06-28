package com.universe.uni.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    private String getJwtFromRequest(HttpServletRequest request) {

        final String TOKEN_TYPE = "Bearer ";

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith(TOKEN_TYPE)) {
            return header.substring(TOKEN_TYPE.length());
        }
        return null;
    }
}
