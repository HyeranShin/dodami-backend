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
public class Letter extends BaseTimeEntity {

    @Id
    @GeneratedValue
    Long idx;

    @Column
    Long userIdx;

    @Column
    String title;

    @Column
    String content;

    @Builder
    public Letter(Long userIdx, String title, String content) {
        this.userIdx = userIdx;
        this.title = title;
        this.content = content;
    }
}
