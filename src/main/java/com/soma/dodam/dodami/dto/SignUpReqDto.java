package com.soma.dodam.dodami.dto;

import com.soma.dodam.dodami.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

@Getter
public class SignUpReqDto {
    private final static String ID_REGEX = "^[a-z0-9]{4,12}$";
    private final static String PASSWORD_REGEX = "^[a-zA-Z0-9]{8,20}$";
    private final static String NAME_REGEX = "^[가-힣]{2,}$";
    private final static String PHONE_REGEX = "^01[0|1|6-9][0-9]{3,4}[0-9]{4}$";

    @ApiModelProperty(notes = "4~12자의 영문 소문자나 숫자", example = "dodami", position = 1)
    @Pattern(regexp = ID_REGEX, message = "아이디 형식이 올바르지 않습니다.")
    private String id;

    @ApiModelProperty(notes = "8~20자의 영어 대소문자나 숫자", example = "dodami123", position = 2)
    @Pattern(regexp = PASSWORD_REGEX, message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

    @ApiModelProperty(notes = "password와 일치", example = "dodami123", position = 3)
    private String configPassword;

    @ApiModelProperty(notes = "2자 이상의 한글", example = "도담이", position = 4)
    @Pattern(regexp = NAME_REGEX, message = "이름 형식이 올바르지 않습니다.")
    private String name;

    @ApiModelProperty(notes = "\n", example = "010-1234-1234", position = 5)
    @Pattern(regexp = PHONE_REGEX, message = "휴대폰 반호 형식이 올바르지 않습니다.")
    private String phone;

    @AssertTrue(message = "비밀번호와 비밀번호 확인이 일치하지 않습니다.")
    private boolean isEualConfigPassword() {
        return password.equals(configPassword);
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .password(password)
                .name(name)
                .phone(phone)
                .build();
    }
}
