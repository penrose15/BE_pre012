package com.codestates.pre012.posts.dto;

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
    @Builder
    @AllArgsConstructor
    public static class PostsResponse {

        private long postsId;
        private String title;
        private String content;

    }

    //전체페이지에 댓글을 내보낼 필요는 없으므로 전체 페이지 responseDTO 추가
    @Getter
    @Builder
    @AllArgsConstructor
    public static class SearchResponse {

        private long postsId;
        private String title;
        private String content;
        private int view;
        private List<ReplyDto.Response> replies;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReplyResponse {

        private long replyId;
        private String content;
    }


}