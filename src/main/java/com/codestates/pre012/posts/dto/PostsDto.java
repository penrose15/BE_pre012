package com.codestates.pre012.posts.dto;

import lombok.*;

public class PostsDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        private String title;

        private String content;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        private long postsId;

        private String title;

        private String content;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private long postsId;
        private String title;
        private String content;

        public Response(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }
}