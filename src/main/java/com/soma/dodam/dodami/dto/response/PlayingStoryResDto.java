package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.PlayingStory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PlayingStoryResDto {

    @ApiModelProperty(required = true, notes = "컨텐츠 Index", example = "1", position = 1)
    private Long contentsIdx;

    @ApiModelProperty(required = true, notes = "재생한 시간(초 단위)", example = "120", position = 2)
    private Long playedTime;

    public PlayingStoryResDto(PlayingStory playingStory) {
        this.contentsIdx = playingStory.getContentsIdx();
        this.playedTime = playingStory.getPlayedTime();
    }
}
