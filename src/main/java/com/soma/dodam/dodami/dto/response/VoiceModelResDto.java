package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.VoiceModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class VoiceModelResDto {

    private Long idx;

    @ApiModelProperty(example = "아빠")
    private String name;

    public VoiceModelResDto(VoiceModel voiceModel) {
        this.idx = voiceModel.getIdx();
        this.name = voiceModel.getName();
    }
}
