package com.codestates.pre012.tag.service;

import com.codestates.pre012.exception.BusinessLogicException;
import com.codestates.pre012.exception.ExceptionCode;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.repository.PostsRepository;
import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.repository.TagPostsRepository;
import com.codestates.pre012.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public Tag saveOrFindTag(Tag tag) {
        Optional<Tag> findTag = tagRepository.findByTagList(tag.getTagList());
        return findTag.orElseGet(() -> tagRepository.save(new Tag(tag.getTagList())));
    }

    public Page<Tag> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Tag> tagPage = tagRepository.findAll(pageable);

        return tagPage;
    }


}
