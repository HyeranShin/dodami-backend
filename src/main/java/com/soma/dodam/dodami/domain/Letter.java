package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "letter", schema = "public")
public class Letter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long userIdx;

    @Column
    private String title;

    @Column
    private String content;

    @Builder
    public Letter(Long userIdx, String title, String content) {
        this.userIdx = userIdx;
        this.title = title;
        this.content = content;
    }
}
