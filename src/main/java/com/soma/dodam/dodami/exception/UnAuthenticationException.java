package com.soma.dodam.dodami.exception;

import lombok.Getter;

@Getter
public class UnAuthenticationException extends Throwable {
    private String field;

    public UnAuthenticationException(String field, String message) {
        super(message);
        this.field = field;
    }
}
