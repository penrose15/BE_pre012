package com.codestates.pre012.tag.service;

import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;




}
