package com.codestates.pre012.posts.controller;


import com.codestates.pre012.config.oauth.PrincipalDetails;
import com.codestates.pre012.dto.MultiResponseDto;
import com.codestates.pre012.dto.SingleResponseDto;
import com.codestates.pre012.posts.dto.PostsDto;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.mapper.PostsMapper;
import com.codestates.pre012.posts.service.PostsService;
import com.codestates.pre012.reply.ReplyMapper;
import com.codestates.pre012.reply.ReplyService;
import com.codestates.pre012.tag.TagMapper;
import com.codestates.pre012.tag.dto.TagDto;
import com.codestates.pre012.tag.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/posts")
@Validated // queryParameter 유효성 검증에 필요
public class PostsController {

    private final PostsService postsService;
    private final PostsMapper mapper;

    public PostsController(PostsService postsService, PostsMapper mapper) {
        this.postsService = postsService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseEntity createPosts(@Valid @RequestBody PostsDto.Post posts,
                                      @AuthenticationPrincipal PrincipalDetails principal) {

        Posts findPosts = mapper.postsPostDtoToPosts(posts);

        Posts response = postsService.savedPosts(findPosts, principal.getMember());

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postsToPostsDtoResponse(response)), HttpStatus.CREATED);
    }


    @PatchMapping("/patch")
    public ResponseEntity patchPosts(@Valid @RequestBody PostsDto.Patch posts, @AuthenticationPrincipal PrincipalDetails principal) {


        posts.setPostsId(posts.getPostsId());
        Posts response = postsService.updatePosts(mapper.postsPatchDtoToPosts(posts), principal.getMember());

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postsToPostsDtoResponse(response)), HttpStatus.OK);
    }

    @GetMapping("/{posts-id}")
    public ResponseEntity viewPosts(@PathVariable("posts-id") @Positive Long postId,
                                    @RequestParam(value = "page") int replyPage,
                                    @RequestParam(value = "size") int replySize) {

        Posts response = postsService.lookPosts(postId);


        System.out.println("# post 시작 =======================");
        return new ResponseEntity<>(mapper.postsToPostsDtoResponse(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findPosts(@RequestParam @Positive int page,
                                    @RequestParam @Positive int size) {

        Page<Posts> pagePosts = postsService.findAllPosts(page - 1, size);

        List<Posts> posts = pagePosts.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.postsToPostsDtoPostPageResponses(posts), pagePosts),
                HttpStatus.OK);
    }


    @DeleteMapping("/{posts-Id}")
    public ResponseEntity deletePosts(@PathVariable("posts-Id") @Positive Long postId, @AuthenticationPrincipal PrincipalDetails principal) {

        postsService.deletePosts(postId, principal.getMember());

        return new ResponseEntity<>("삭제 완료", HttpStatus.NO_CONTENT);
    }
}
