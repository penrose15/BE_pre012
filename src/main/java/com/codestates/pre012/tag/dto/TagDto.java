package com.codestates.pre012.tag.dto;

import com.codestates.pre012.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class TagDto {
    @Getter
    @Setter
    public static class Post {

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String tagName;

    }
}
