package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.Letter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class LetterResDto {

    @ApiModelProperty(position = 1)
    private Long idx;

    @ApiModelProperty(notes = "제목", example = "행복이에게", position = 2)
    private String title;

    @ApiModelProperty(notes = "내용", example = "행복아 사랑해", position = 3)
    private String content;

    @ApiModelProperty(notes = "작성 날짜", example = "2019.08.07", position = 4)
    private String date;

    public LetterResDto(Letter letter) {
        this.idx = letter.getIdx();
        this.title = letter.getTitle();
        this.content = letter.getContent();
        this.date = convertDate(letter.getModifiedDate());
    }

    private String convertDate(LocalDateTime localDateTime) {
        String convertedDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        return convertedDate;
    }
}
