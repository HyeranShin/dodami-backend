package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    @Column
    private String imgUrl;

    @Column
    private Integer progress;

    @Builder
    public VoiceModel(Long userIdx, String name, String imgUrl, Integer progress) {
        this.userIdx = userIdx;
        this.name = name;
        this.imgUrl = imgUrl;
        this.progress = progress;
    }

    public VoiceModel updateName(String name) {
        this.name = name;
        return this;
    }

    public VoiceModel updateImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public VoiceModel updateProgress(Integer progress) {
        this.progress = progress;
        return this;
    }
}
