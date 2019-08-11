package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.Contents;
import com.soma.dodam.dodami.domain.ContentsImg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ContentsResDto {

    @ApiModelProperty(required = true, notes = "컨텐츠 Index", example = "1", position = 1)
    private Long contentsIdx;

    @ApiModelProperty(required = true, notes = "제목", example = "친절한 백화점", position = 2)
    private String title;

    @ApiModelProperty(required = true, notes = "줄거리", example = "옛날 미얀마라는 더운 나라에서 생긴 이야기야. 이 나라의 임금님은 값진 코끼리를 스물아홉마리나 가지고 있지만...", position = 3)
    private String summary;

    @ApiModelProperty(required = true, notes = "본문", position = 4)
    private String mainText;

    @ApiModelProperty(required = true, notes = "재생 시간(초 단위)", example = "1800", position = 5)
    private Long playTime;

    @ApiModelProperty(notes = "컨텐츠 이미지", position = 6)
    private List<ContentsImgResDto> imgUrlResDto;

    public ContentsResDto(Contents contents, List<ContentsImg> contentsImgs) {
        this.contentsIdx = contents.getIdx();
        this.title = contents.getTitle();
        this.summary = contents.getSummary();
        this.mainText = contents.getMainText();
        this.playTime = contents.getPlayTime();
        this.imgUrlResDto = toResDto(contentsImgs);
    }

    public List<ContentsImgResDto> toResDto(List<ContentsImg> contentsImgs) {
        List<ContentsImgResDto> imgUrls = contentsImgs
                .stream()
                .map(contentsImg -> new ContentsImgResDto(contentsImg))
                .collect(Collectors.toList());

        return imgUrls;
    }
}
