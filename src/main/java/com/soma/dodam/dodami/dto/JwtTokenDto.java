package com.soma.dodam.dodami.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtTokenDto {

    @ApiModelProperty(example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIke0pXVC5JU1NVRVIiLCJ1c2VyX2lkeCI6MX0.8hjiKaoR016zU1u9z7JygRpcoMufVtj6UCs3uEarYl4")
    private String token;

    @Builder
    public JwtTokenDto(String token) {
        this.token = token;
    }
}
