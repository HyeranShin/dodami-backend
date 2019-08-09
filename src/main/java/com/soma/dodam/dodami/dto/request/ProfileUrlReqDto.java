package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ProfileUrlReqDto {

    @ApiModelProperty(required = true, notes = "이미지 url", position = 1)
    private String profileUrl;
}
