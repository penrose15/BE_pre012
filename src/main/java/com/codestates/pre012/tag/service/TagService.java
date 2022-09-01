package com.codestates.pre012.tag.service;

import com.codestates.pre012.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;




}
