package com.soma.dodam.dodami.exception;

import lombok.Getter;

@Getter
public class NoResultException extends RuntimeException {

    private String field;

    public NoResultException(String field, String message) {
        super(message);
        this.field = field;
    }
}
