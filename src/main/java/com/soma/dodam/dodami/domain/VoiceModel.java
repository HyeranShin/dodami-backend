package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class VoiceModel extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long idx;

    @Column
    private Long userIdx;

    @Column
    private String name;

//    @Column
//    private String filePath;

    @Builder
    public VoiceModel(Long userIdx, String name) {
        this.userIdx = userIdx;
        this.name = name;
    }
}
