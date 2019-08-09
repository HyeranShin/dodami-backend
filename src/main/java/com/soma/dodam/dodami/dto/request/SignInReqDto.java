package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SignInReqDto {

    @ApiModelProperty(required = true, example = "dodami@naver.com", position = 1)
    private String id;

    @ApiModelProperty(required = true, example = "dodami123", position = 2)
    private String password;

}
