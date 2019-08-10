package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SubscriptionReqDto {

    @ApiModelProperty(required = true, notes = "구독권 번호", example = "1", position = 1)
    private Integer idx;
}
