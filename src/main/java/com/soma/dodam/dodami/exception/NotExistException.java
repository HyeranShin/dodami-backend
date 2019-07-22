package com.soma.dodam.dodami.exception;

import lombok.Getter;

@Getter
public class NotExistException extends RuntimeException {

    private String field;

    public NotExistException(String field, String message) {
        super(message);
        this.field = field;
    }
}