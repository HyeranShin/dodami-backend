package com.soma.dodam.dodami.dto.response;

import com.soma.dodam.dodami.domain.Subscription;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SubscriptionResDto {

    @ApiModelProperty(notes = "번호(1부터 가격 오름차순)", example = "1", position = 1)
    private Integer idx;

    @ApiModelProperty(notes = "이름", example = "도담 비기너", position = 2)
    private String name;

    @ApiModelProperty(notes = "설명", example = "모델 5명 등록", position = 3)
    private String description;

    @ApiModelProperty(notes = "가격", example = "3000", position = 4)
    private Integer price;

    public SubscriptionResDto(Subscription subscription) {
        this.idx = subscription.getIdx();
        this.name = subscription.getName();
        this.description = subscription.getDescription();
        this.price = subscription.getPrice();
    }
}
