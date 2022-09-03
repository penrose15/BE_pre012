package com.codestates.pre012.tag.controller;

import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.mapper.PostsMapper;
import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.service.TagPostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/v1/tag")
public class TagController {

    private final TagPostService tagPostService;
    private final PostsMapper postsMapper;

    @GetMapping("/{tag}")
    public ResponseEntity findPosts(@PathVariable("tag") String tag) {
        Tag.TagList tagList = Tag.TagList.valueOf(tag);
        List<Posts> postsList = tagPostService.findPostsByTag(tagList);

        return new ResponseEntity<>(postsMapper.postsToPostsDtoResponses(postsList), HttpStatus.OK);
    }
}
