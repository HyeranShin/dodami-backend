package com.soma.dodam.dodami.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResDto {

    @ApiModelProperty(example = "dodami")
    private String id;

    @ApiModelProperty(example = "dodami123")
    private String password;

    @ApiModelProperty(example = "도담이")
    private String name;

    @ApiModelProperty(example = "010-1234-1234")
    private String phone;
}
