package com.codestates.pre012.tag.service;

import com.codestates.pre012.exception.BusinessLogicException;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.service.PostsService;
import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.entity.TagPosts;
import com.codestates.pre012.tag.repository.TagPostsRepository;
import com.codestates.pre012.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class TagPostService {

    private final TagPostsRepository tagPostsRepository;
    private final TagService tagService;

    public TagPosts saveTagPosts(Posts posts,TagPosts tagPosts ,Tag tag) {

        tagPosts.setPosts(posts);
        tag = tagService.saveOrFindTag(tag);
        tagPosts.setTag(tag);

        return tagPostsRepository.save(tagPosts);
    }

    public void deleteTagPosts(long tagPostsId) {
        Optional<TagPosts> tagPosts = tagPostsRepository.findById(tagPostsId);
        TagPosts verifiedTagPosts = tagPosts.orElseThrow(() -> new RuntimeException("tagpost 존재하지 않음"));

        tagPostsRepository.delete(verifiedTagPosts);
    }

    public Page<Posts> findPostsByTag(Tag.TagList tagList, int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size);

        Page<Posts> postsPage = tagPostsRepository.findPostsByTag(tagList, pageable);

        return postsPage;
    }

}
