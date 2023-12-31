package com.universe.uni.domain.entity;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
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

import lombok.Builder;
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

	@Column(name = "content", length = 54)
	private String content;

	@Column(name = "is_visible", nullable = false)
	private boolean isVisible;

	@Column(name = "is_used", nullable = false)
	private boolean isUsed;

	@Column(name = "used_at")
	private LocalDateTime usedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

	@Column(name = "game_type", nullable = false)
	@Convert(converter = GameTypeAttributeConverter.class)
	private GameType gameType;

	@Builder
	public WishCoupon(String image, String content, boolean isVisible,
		Game game, GameType gameType) {
		this.image = image;
		this.content = content;
		this.isVisible = isVisible;
		this.isUsed = Boolean.FALSE;
		this.game = game;
		this.gameType = gameType;
	}

	public void updateContent(String content) {
		this.content = content;
	}

	public void makeVisible() {
		this.isVisible = true;
	}

	public void useWishCoupon() {
		this.isUsed = true;
		this.usedAt = LocalDateTime.now();
	}

	public void setWinnerToWishCoupon(User user) {
		this.user = user;
	}
}
