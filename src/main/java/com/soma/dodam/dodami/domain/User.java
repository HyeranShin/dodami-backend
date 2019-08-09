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
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long idx;

    @Column
    private String name;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String babyName;

    @Column
    private String phone;

    @Column
    private String profileUrl;

    @Builder
    public User(String name, String id, String password, String babyName, String phone, String profileUrl) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.babyName = babyName;
        this.phone = phone;
        this.profileUrl = profileUrl;
    }

    public User updateProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
        return this;
    }

//    public User updatePassword(String password) {
//        this.password = password;
//        return this;
//    }
//
//    public User updatePhone(String phone) {
//        this.phone = phone;
//        return this;
//    }
}
