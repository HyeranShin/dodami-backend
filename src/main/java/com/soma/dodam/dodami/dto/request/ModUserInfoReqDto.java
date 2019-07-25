package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ModUserInfoReqDto {

    @ApiModelProperty(example = "010-1234-1234", position = 1)
    private String phone;

    @ApiModelProperty(example = "dodami123", position = 2)
    private String password;
}
