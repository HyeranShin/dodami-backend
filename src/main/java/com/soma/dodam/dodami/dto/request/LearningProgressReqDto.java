package com.soma.dodam.dodami.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LearningProgressReqDto {

    @ApiModelProperty(readOnly = true)
    private Long userIdx;

    @ApiModelProperty(readOnly = true)
    private Long idx;

    @ApiModelProperty(required = true, notes = "음성 모델 학습 진행 상황", example = "50")
    private Integer progress;
}
