package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.ContentsMainText;
import lombok.Getter;

@Getter
public class ContentsMainTextResDto {

    private Long time;

    private String text;

    public ContentsMainTextResDto(ContentsMainText contentsMainText) {
        this.time = contentsMainText.getTime();
        this.text = contentsMainText.getText();
    }
}
