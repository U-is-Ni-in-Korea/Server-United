package com.universe.uni.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.universe.uni.domain.GameType;
import com.universe.uni.domain.entity.convertor.GameTypeAttributeConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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

	@Column(name = "content", length = 54)
	private String content;

	@Column(name = "is_visible", nullable = false)
	private boolean isVisible;

	@Column(name = "is_used", nullable = false)
	private boolean isUsed;

	@Column(name = "used_at")
	private LocalDateTime usedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

	@Column(name = "game_type", nullable = false)
	@Convert(converter = GameTypeAttributeConverter.class)
	private GameType gameType;
}
