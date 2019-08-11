package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Contents {

    @Id
    @GeneratedValue
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
}
