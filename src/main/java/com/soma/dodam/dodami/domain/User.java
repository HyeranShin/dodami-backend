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

    public boolean matchPasswordBy(SignInReqDto signInReqDto) {
        if(!this.password.equals(signInReqDto.getPassword())) {
            throw new NotMatchException("password", "비밀번호가 일치하지 않습니다.");
        }
        return true;
    }
}
