package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "contents_voice", schema = "public")
public class ContentsVoice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long userIdx;

    @Column
    private Long voiceModelIdx;

    @Column
    private Long contentsIdx;

    @Column
    private String wavUrl;
}
