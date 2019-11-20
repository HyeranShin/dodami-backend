package com.soma.dodam.dodami.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.soma.dodam.dodami.domain.Contents;
import com.soma.dodam.dodami.domain.ContentsImg;
import com.soma.dodam.dodami.domain.PlayingStory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlayingStoryResDto {

    @ApiModelProperty(required = true, notes = "컨텐츠 Index", example = "1", position = 1)
    private Long contentsIdx;

    @ApiModelProperty(required = true, notes = "카테고리 Index", example = "1", position = 2)
    private Integer categoryIdx;

    @ApiModelProperty(required = true, notes = "컨텐츠 제목", example = "아기돼지 삼형제", position = 3)
    private String title;

    @ApiModelProperty(required = true, notes = "컨텐츠 이미지", position = 4)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ContentsImgResDto> imgUrlResDto;

    @ApiModelProperty(required = true, notes = "총 재생 시간", example = "300", position = 5)
    private Long playTime;

    @ApiModelProperty(required = true, notes = "재생한 시간(초 단위)", example = "120", position = 6)
    private Long playedTime;

    public PlayingStoryResDto(PlayingStory playingStory, Contents contents, List<ContentsImg> contentsImg) {
        this.contentsIdx = playingStory.getContentsIdx();
        this.categoryIdx = contents.getCategoryIdx();
        this.title = contents.getTitle();
        this.imgUrlResDto = toImgResDto(contentsImg);
        this.playTime = contents.getPlayTime();
        this.playedTime = playingStory.getPlayedTime();
    }

    public List<ContentsImgResDto> toImgResDto(List<ContentsImg> contentsImgs) {
        List<ContentsImgResDto> imgUrls = contentsImgs
                .stream()
                .map(contentsImg -> new ContentsImgResDto(contentsImg))
                .collect(Collectors.toList());

        return imgUrls;
    }
}
