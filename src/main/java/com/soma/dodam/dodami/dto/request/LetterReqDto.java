package com.soma.dodam.dodami.dto.request;

import com.soma.dodam.dodami.domain.Letter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterReqDto {

    @ApiModelProperty(readOnly = true)
    private Long userIdx;

    @ApiModelProperty(example = "행복이에게", position = 1)
    private String title;

    @ApiModelProperty(example = "행복아 사랑해", position = 2)
    private String content;

    public Letter toLetter() {
        return Letter.builder()
                .userIdx(userIdx)
                .title(title)
                .content(content)
                .build();
    }
}
