package com.universe.uni.config.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtManager {

	@Value(value = "${jwt.secret}")
	private String jwtSecret;

	@Value(value = "${jwt.expiryPeriod}")
	private Long accessTokenExpiryPeriod;

	@PostConstruct
	protected void init() {
		jwtSecret = Base64.getEncoder()
			.encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}

	public String createToken(String userId) {
		ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
		OffsetDateTime now = OffsetDateTime.now(seoulZoneId);
		OffsetDateTime expiration = now.plusSeconds(accessTokenExpiryPeriod);

		final Claims claims = Jwts.claims()
			.setSubject("accessToken")
			.setIssuedAt(toDate(now))
			.setExpiration(toDate(expiration));

		claims.put("userId", userId);

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setClaims(claims)
			.signWith(getSigningKey())
			.compact();
	}

	private Date toDate(OffsetDateTime offsetDateTime) {
		return Date.from(offsetDateTime.toInstant());
	}

	private Key getSigningKey() {
		final byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean verifyToken(String token) {
		try {
			final Claims claims = getBody(token);
			return true;
		} catch (MalformedJwtException e) {
			log.error("INVALID_JWT_TOKEN");
		} catch (ExpiredJwtException e) {
			log.error("EXPIRED_JWT_TOKEN");
		} catch (UnsupportedJwtException e) {
			log.error("UNSUPPORTED_JWT_TOKEN");
		} catch (JwtException e) {
			log.error(e.getMessage());
		}
		return false;
	}

	private Claims getBody(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJwt(token)
			.getBody();
	}

	public Long getUserIdFromJwt(String token) {
		Claims claims = getBody(token);
		return claims.get("userId", Long.class);
	}
}
