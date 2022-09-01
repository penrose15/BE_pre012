package com.codestates.pre012.tag.service;

import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.entity.Tag_Posts;
import com.codestates.pre012.tag.repository.TagRepository;
import com.codestates.pre012.tag.repository.Tag_PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Tag_PostsService {
    private final Tag_PostsRepository tag_postsRepository;
    private TagService tagService;

}
