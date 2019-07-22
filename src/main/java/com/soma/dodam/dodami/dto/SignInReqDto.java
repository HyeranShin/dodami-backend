package com.soma.dodam.dodami.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SignInReqDto {

    @ApiModelProperty(example = "dodami", position = 1)
    private String id;

    @ApiModelProperty(example = "dodami123", position = 2)
    private String password;

}
