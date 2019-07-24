package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class VoiceModelModReqDto {

    private Long idx;

    @ApiModelProperty(example = "아빠")
    private String name;
}
