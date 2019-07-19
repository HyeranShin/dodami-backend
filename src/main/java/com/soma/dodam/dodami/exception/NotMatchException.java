package com.soma.dodam.dodami.exception;

import lombok.Getter;

@Getter
public class NotMatchException extends RuntimeException {

    private String field;

    public NotMatchException(String field, String message) {
        super(message);
        this.field = field;
    }
}