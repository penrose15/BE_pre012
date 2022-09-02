package com.codestates.pre012.tag.service;

import com.codestates.pre012.exception.BusinessLogicException;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.service.PostsService;
import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.entity.TagPosts;
import com.codestates.pre012.tag.repository.TagPostsRepository;
import com.codestates.pre012.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TagPostService {

    private final TagPostsRepository tagPostsRepository;
    private final TagRepository tagRepository;

    public TagPosts saveTagPosts(Posts posts,TagPosts tagPosts ,Tag tag) {

        tagPosts.setPosts(posts);
        tagPosts.setTag(tag);

        return tagPostsRepository.save(tagPosts);
    }

    public TagPosts updateTagPosts(TagPosts tagPosts, Tag tag) {
        Optional<TagPosts> findTagPosts = tagPostsRepository.findById(tagPosts.getTagPostsId());
        TagPosts tagPosts1 = findTagPosts.orElseThrow(() ->new RuntimeException("# 존재하지 않는 tagPosts"));

        Optional<Tag> findTag = tagRepository.findByTagList(tag.getTagList());

        Tag tag1 = findTag.orElseGet(() -> tagRepository.save(new Tag(tag.getTagList())));
        if(!tagPosts1.getTag().getTagList().equals(tag1.getTagList())) {
            tagPosts1.setTag(tag1);
        }

        return tagPostsRepository.save(tagPosts1);
    }

    public void deleteTagPosts(long tagPostsId) {
        Optional<TagPosts> tagPosts = tagPostsRepository.findById(tagPostsId);
        TagPosts verifiedTagPosts = tagPosts.orElseThrow(() -> new RuntimeException("tagpost 존재하지 않음"));

        tagPostsRepository.delete(verifiedTagPosts);
    }

}
