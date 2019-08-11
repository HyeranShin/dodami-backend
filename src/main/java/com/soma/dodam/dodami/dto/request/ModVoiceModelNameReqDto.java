package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModVoiceModelNameReqDto {

    @ApiModelProperty(readOnly = true)
    private Long idx;

    @ApiModelProperty(example = "아빠", position = 1)
    private String name;
}
