package com.soma.dodam.dodami.dto.request;

import com.soma.dodam.dodami.domain.VoiceModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoiceModelReqDto {

    @ApiModelProperty(readOnly = true)
    private Long userIdx;

    @ApiModelProperty(example = "아빠")
    private String name;

    public VoiceModel toVoiceModel() {
        return VoiceModel.builder()
                .userIdx(userIdx)
                .name(name)
                .build();
    }
}
