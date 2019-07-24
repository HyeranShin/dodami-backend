package com.soma.dodam.dodami.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtTokenDto {

    private String token;

    @Builder
    public JwtTokenDto(String token) {
        this.token = token;
    }
}
