package com.soma.dodam.dodami.domain;

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
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String phone;

    @Builder
    public User(String id, String password, String name, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public User updatePassword(String password) {
        this.password = password;
        return this;
    }

    public User updatePhone(String phone) {
        this.phone = phone;
        return this;
    }
}
