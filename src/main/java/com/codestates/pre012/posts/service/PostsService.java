package com.codestates.pre012.posts.service;

import com.codestates.pre012.posts.repository.PostsRepository;
import org.springframework.stereotype.Service;
//postsservice 추가, repository 의존성 주입
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }
}
