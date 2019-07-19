package com.soma.dodam.dodami.exception;

import lombok.Getter;

@Getter
public class AlreadyExistsException extends RuntimeException {

    private String field;

    public AlreadyExistsException(String field, String message) {
        super(message);
        this.field = field;
    }
}
