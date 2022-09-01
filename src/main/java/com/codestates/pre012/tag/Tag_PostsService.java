package com.codestates.pre012.tag;

import com.codestates.pre012.tag.Tag_PostsRepository;
import com.codestates.pre012.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Tag_PostsService {
    private final Tag_PostsRepository tag_postsRepository;
    private TagService tagService;

}
