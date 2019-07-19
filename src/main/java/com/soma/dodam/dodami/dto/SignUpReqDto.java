package com.soma.dodam.dodami.dto;

import com.soma.dodam.dodami.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SignUpReqDto {

    @ApiModelProperty(notes = "아이디", example = "dodami", position = 1)
    private String id;

    @ApiModelProperty(notes = "비밀번호", example = "dodami123", position = 2)
    private String password;

    @ApiModelProperty(notes = "비밀번호 확인", example = "dodami123", position = 3)
    private String configPassword;

    @ApiModelProperty(notes = "이름", example = "도담이", position = 4)
    private String name;

    @ApiModelProperty(notes = "휴대폰 번호", example = "010-1234-1234", position = 5)
    private String phone;

    public User toUser() {
        return User.builder()
                .id(id)
                .password(password)
                .name(name)
                .phone(phone)
                .build();
    }
}
