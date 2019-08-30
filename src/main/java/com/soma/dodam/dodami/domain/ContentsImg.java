package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "contents_img", schema = "public")
public class ContentsImg extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long contentsIdx;

    @Column
    private String imgUrl;
}
