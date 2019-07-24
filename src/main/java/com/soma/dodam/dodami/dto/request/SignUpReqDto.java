package com.soma.dodam.dodami.dto.request;

import com.soma.dodam.dodami.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class SignUpReqDto {

    @ApiModelProperty(notes = "4~12자의 영문 소문자 또는 숫자", example = "dodami", position = 1)
    private String id;

    @ApiModelProperty(notes = "8~20자의 영문 대소문자 또는 숫자", example = "dodami123", position = 2)
    private String password;

    @ApiModelProperty(notes = "password와 일치", example = "dodami123", position = 3)
    private String configPassword;

    @ApiModelProperty(notes = "2자 이상의 한글", example = "도담이", position = 4)
    private String name;

    @ApiModelProperty(notes = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$", example = "010-1234-1234", position = 5)
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phone(phone)
                .build();
    }
}
