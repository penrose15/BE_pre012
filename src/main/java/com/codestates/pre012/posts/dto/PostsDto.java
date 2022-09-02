package com.codestates.pre012.posts.dto;

import com.codestates.pre012.member.dto.MemberDto;
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
        private int view;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SearchResponse {

        private long postsId;
        private String title;
        private String content;
        private int view;
        private MemberDto.Response member;
        private List<ReplyDto.Response> replies;
    }
}