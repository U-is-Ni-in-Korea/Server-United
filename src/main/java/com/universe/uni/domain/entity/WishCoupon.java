package com.universe.uni.domain.entity;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.universe.uni.domain.entity.convertor.GameTypeAttributeConverter;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.universe.uni.domain.GameType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wish_coupon")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class WishCoupon {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "wish_coupon_id")
	private Long id;

	@Column(name = "image")
	private String image;

	@Column(name = "content")
	private String content;

	@Column(name = "is_visible", nullable = false)
	private boolean isVisible;

	@Column(name = "is_used", nullable = false)
	private boolean isUsed;

	@Column(name = "used_at")
	private LocalDateTime usedAt;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "game_id", nullable = false)
	private Long gameId;

	@Column(name = "game_type", nullable = false)
	@Convert(converter = GameTypeAttributeConverter.class)
	private GameType gameType;
}
