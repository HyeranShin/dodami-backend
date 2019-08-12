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
@Getter
@Entity
public class PlayingStory {

    @Id
    @GeneratedValue
    private Long idx;

    @Column
    private Long userIdx;

    @Column
    private Long contentsIdx;

    @Column
    private Long playedTime;

    @Builder
    public PlayingStory(Long userIdx, Long contentsIdx, Long playedTime) {
        this.userIdx = userIdx;
        this.contentsIdx = contentsIdx;
        this.playedTime = playedTime;
    }
}
