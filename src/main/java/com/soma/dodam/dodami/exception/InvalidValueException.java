package com.soma.dodam.dodami.exception;

import lombok.Getter;

@Getter
public class InvalidValueException extends RuntimeException {

    private String field;

    public InvalidValueException(String field, String message) {
        super(message);
        this.field = field;
    }
}
