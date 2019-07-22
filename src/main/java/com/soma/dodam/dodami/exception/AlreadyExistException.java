package com.soma.dodam.dodami.exception;

import lombok.Getter;

@Getter
public class AlreadyExistException extends RuntimeException {

    private String field;

    public AlreadyExistException(String field, String message) {
        super(message);
        this.field = field;
    }
}
