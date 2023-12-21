package com.universe.uni.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.universe.uni.domain.MissionTool;
import com.universe.uni.domain.MissionType;
import com.universe.uni.domain.entity.convertor.MissionToolAttributeConverter;
import com.universe.uni.domain.entity.convertor.MissionTypeAttributeConverter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "mission_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_category_id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rule", nullable = false)
    private String rule;

    @Column(name = "tip", nullable = false)
    private String tip;

    @Column(name = "example", nullable = false)
    private String example;

    @Column(name = "image", nullable = false)
    private String image;

    @Deprecated
    @Column(name = "level", nullable = false)
    private int level;

    @Deprecated
    @Column(name = "expected_time", nullable = false)
    private int expectedTime;

    @Column(name = "mission_type")
    @Convert(converter = MissionTypeAttributeConverter.class)
    private MissionType missionType;

    @Column(name = "mission_tool", nullable = false)
    @Convert(converter = MissionToolAttributeConverter.class)
    private MissionTool missionTool;

    public boolean isMissionTypeSame() {
        return this.missionType.equals(MissionType.SAME);
    }
}
