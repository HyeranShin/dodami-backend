package com.soma.dodam.dodami.dto.request;

import com.soma.dodam.dodami.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SignUpReqDto {

    @ApiModelProperty(required = true, notes = "이름(2자 이상의 한글)", example = "도담이", position = 1)
    private String name;

    @ApiModelProperty(required = true, notes = "이메일", example = "dodami@naver.com", position = 2)
    private String id;

    @ApiModelProperty(required = true, notes = "비밀번호(8~20자의 영문 대소문자 또는 숫자)", example = "dodami123", position = 3)
    private String password;

//    @ApiModelProperty(notes = "password와 일치", example = "dodami123", position = 3)
//    private String configPassword;

    @ApiModelProperty(notes = "아기 이름", example = "사랑이", position = 4)
    private String babyName;

    @ApiModelProperty(notes = "휴대폰 번호", example = "010-1234-1234", position = 5)
    private String phone;

    @ApiModelProperty(notes = "프로필 이미지 주소", position = 6)
    private String profileUrl;

    public User toUser(String encodedPassword) {
        return User.builder()
                .name(name)
                .id(id)
                .password(encodedPassword)
                .babyName(babyName)
                .phone(phone)
                .profileUrl(profileUrl)
                .build();
    }
}
