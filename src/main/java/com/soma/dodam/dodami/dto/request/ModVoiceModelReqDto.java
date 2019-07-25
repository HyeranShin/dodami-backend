package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ModVoiceModelReqDto {

    @ApiModelProperty(position = 1)
    private Long idx;

    @ApiModelProperty(example = "아빠", position = 2)
    private String name;
}
