package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "contents", schema = "public")
public class Contents extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Integer categoryIdx;

    @Column
    private String title;

    @Column
    private String summary;

    @Column
    private String mainText;

    @Column
    private Long playTime;

    @Column
    private String wavUrl;
}
