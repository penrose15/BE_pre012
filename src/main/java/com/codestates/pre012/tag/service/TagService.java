package com.codestates.pre012.tag.service;

import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.repository.TagPostsRepository;
import com.codestates.pre012.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagPostsRepository tagPostsRepository;
    public Tag saveOrFindTag(Tag tag) {
        Optional<Tag> findTag = tagRepository.findByTagList(tag.getTagList());
        return findTag.orElseGet(() -> tagRepository.save(new Tag(tag.getTagList())));
    }

}
