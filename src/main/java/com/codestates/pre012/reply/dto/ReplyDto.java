package com.codestates.pre012.reply.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class ReplyDto {


    @Getter
    public static class Post {

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String content;
    }
    @Getter
    public static class Patch {

        private String content;
    }

    @Getter
    @Builder
    public static class Response {

        private String content;

    }
}
