package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.ContentsVoice;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ContentsVoiceResDto {

    @ApiModelProperty(required = true, notes = "컨텐츠 음성 wav url", position = 1)
    private String wavUrl;

    public ContentsVoiceResDto(ContentsVoice contentsVoice) {
        this.wavUrl = contentsVoice.getWavUrl();
    }
}
