package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.ContentsImg;
import lombok.Getter;

@Getter
public class ContentsImgResDto {

    private String imgUrl;

    public ContentsImgResDto(ContentsImg contentsImg) {
        this.imgUrl = contentsImg.getImgUrl();
    }
}
