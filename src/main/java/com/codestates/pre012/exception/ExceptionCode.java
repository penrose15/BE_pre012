package com.codestates.pre012.exception;

import lombok.Getter;

public enum ExceptionCode {

    INFO_NOT_FOUND(404, "정보가 없습니다."),
    MEMBER_NOT_FOUND(404, "멤버가 존재하지 않습니다."),
    MEMBER_EXIST(404,"멤버가 이미 존재합니다."),
    POSTS_NOT_FOUND(404,"포스트가 존재하지 않습니다. ");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
