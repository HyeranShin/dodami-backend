package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "playing_story", schema = "public")
public class PlayingStory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
