package com.soma.dodam.dodami.dto.response;

import lombok.Getter;

@Getter
public class ResourceResDto {

    private String resourceUrl;

    public ResourceResDto(String url) {
        this.resourceUrl = url;
    }
}