package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.VoiceModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class VoiceModelResDto {

    @ApiModelProperty(position = 1)
    private Long idx;

    @ApiModelProperty(notes = "이름 또는 애칭", example = "아빠", position = 2)
    private String name;

    @ApiModelProperty(notes = "음성 모델 이미지 url", position = 3)
    private String imgUrl;

    @ApiModelProperty(notes = "학습 진행 상황", example = "30", position = 4)
    private Integer progress;

    public VoiceModelResDto(VoiceModel voiceModel) {
        this.idx = voiceModel.getIdx();
        this.name = voiceModel.getName();
        this.imgUrl = voiceModel.getImgUrl();
        this.progress = voiceModel.getProgress();
    }
}
