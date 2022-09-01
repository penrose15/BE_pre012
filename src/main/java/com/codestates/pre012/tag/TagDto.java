package com.codestates.pre012.tag;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class TagDto {
    @Getter
    @Setter
    public static class Post {

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String tagName;

    }
}
