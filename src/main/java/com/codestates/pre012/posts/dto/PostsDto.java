package com.codestates.pre012.posts.dto;

import com.codestates.pre012.reply.Reply;
import com.codestates.pre012.reply.dto.ReplyDto;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class PostsDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String title;

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String content;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        @Positive
        private long postsId;

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String title;

        @NotBlank(message = "공백이 될 수 없습니다.")
        private String content;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private long postsId;
        private String title;
        private String content;

        private List<ReplyDto.Response> replies;

        public Response(String title, String content, List<ReplyDto.Response> replies) {
            this.title = title;
            this.content = content;
            this.replies = replies;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostPageResponse {

        private long postsId;
        private String title;
        private String content;

    }


}