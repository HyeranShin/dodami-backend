package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Suggestion extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long Idx;

    @Column
    @NotNull
    private Long userIdx;

    @Column
    @NotNull
    private String content;

    @Builder
    public Suggestion(Long userIdx, String content) {
        this.userIdx = userIdx;
        this.content = content;
    }
}
