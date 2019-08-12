package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PlayedTimeReqDto {

    @ApiModelProperty(required = true, notes = "재생한 시간(초 단위)", position = 1)
    private Long playedTime;

}
