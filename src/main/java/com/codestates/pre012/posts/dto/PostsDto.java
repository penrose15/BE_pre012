package com.codestates.pre012.posts.dto;

import com.codestates.pre012.reply.entity.Reply;
import com.codestates.pre012.reply.dto.ReplyDto;

import lombok.*;

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


    //순환참조를 막기 위해 replyResponseDTO 추가(이유는 모르겠음....) https://dev-coco.tistory.com/132
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private long postsId;
        private String title;
        private String content;
        private int view;
        private List<Reply> replyList;


        private List<ReplyDto.Response> replies;

        public Response(String title, String content, List<ReplyDto.Response> replies) {
            this.title = title;
            this.content = content;
            this.replies = replies;
        }

    }

    //전체페이지에 댓글을 내보낼 필요는 없으므로 전체 페이지 responseDTO 추가
    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostPageResponse {

        private long postsId;
        private String title;
        private String content;

    }


}