package com.soma.dodam.dodami.domain;

import com.soma.dodam.dodami.dto.SignInReqDto;
import com.soma.dodam.dodami.exception.NotMatchException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long idx;

    @Column
    @NotNull
    private String id;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String phone;

    @Builder
    public User(String id, String password, String name, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
