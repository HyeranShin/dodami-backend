package com.soma.dodam.dodami.dto;

import com.soma.dodam.dodami.domain.Suggestion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestionReqDto {

    @ApiModelProperty(readOnly = true)
    private Long userIdx;

    @ApiModelProperty(example = "도담이에게 건의합니다.")
    private String content;

    public Suggestion toSuggestion() {
        return Suggestion.builder()
                .userIdx(userIdx)
                .content(content)
                .build();
    }
}
