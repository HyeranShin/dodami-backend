package com.soma.dodam.dodami.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResDto {

//    @ApiModelProperty(example = "dodami")
//    private String id;

//    @ApiModelProperty(example = "dodami123")
//    private String password;

    @ApiModelProperty(required = true, notes = "이름", example = "도담이", position = 1)
    private String name;

    @ApiModelProperty(notes = "프로필 이미지", position = 2)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String profileUrl;

//    @ApiModelProperty(required = true, notes = "구독권 번호(1부터 가격 오름차순, 0일 경우 구독권 없음)", position = 3)
//    private Integer subscriptionIdx;

    @ApiModelProperty(required = true, notes = "subscriptionIdx가 0이 아닌 경우만 반환", position = 4)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SubscriptionResDto subscriptionResDto;

//    @ApiModelProperty(example = "010-1234-1234")
//    private String phone;
}
