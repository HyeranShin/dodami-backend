package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModVoiceModelImgReqDto {

    @ApiModelProperty(readOnly = true)
    private Long idx;

    @ApiModelProperty(position = 1)
    private String imgUrl;
}
