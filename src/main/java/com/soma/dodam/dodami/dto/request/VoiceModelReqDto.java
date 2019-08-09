package com.soma.dodam.dodami.dto.request;

import com.soma.dodam.dodami.domain.VoiceModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoiceModelReqDto {

    @ApiModelProperty(readOnly = true, position = 1)
    private Long userIdx;

    @ApiModelProperty(required = true, notes = "이름 또는 애칭", example = "아빠", position = 2)
    private String name;

    @ApiModelProperty(notes = "이미지 url", position = 3)
    private String imgUrl;

    public VoiceModel toVoiceModel() {
        return VoiceModel.builder()
                .userIdx(userIdx)
                .name(name)
                .imgUrl(imgUrl)
                .progress(0)
                .build();
    }
}
