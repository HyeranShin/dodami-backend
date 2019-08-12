package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Suggestion extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long Idx;

    @Column
    private Long userIdx;

    @Column
    private String content;

    @Builder
    public Suggestion(Long userIdx, String content) {
        this.userIdx = userIdx;
        this.content = content;
    }
}
