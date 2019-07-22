package com.soma.dodam.dodami.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
    public final static int NAME_PARSE_INDEX = 8;
    public final static int CODES_FIELD_STRING_INDEX = 1;

    @ApiModelProperty(example = "에러 발생 원인", position = 1)
    private String field;

    @ApiModelProperty(example = "에러 내용", position = 2)
    private String message;

    public static ExceptionDto toExceptionDto(ObjectError validError) {
        return ExceptionDto.builder()
                .message(validError.getDefaultMessage())
                .field(validError.getCodes()[CODES_FIELD_STRING_INDEX]
                        .substring(NAME_PARSE_INDEX)).build();
    }

    public static ExceptionDto toExceptionDto(String field, String message) {
        return ExceptionDto.builder().field(field).message(message).build();
    }
}