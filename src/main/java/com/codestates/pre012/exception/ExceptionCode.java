package com.codestates.pre012.exception;

import lombok.Getter;

public enum ExceptionCode {
    INFO_NOT_FOUND(404, "정보가 없습니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
