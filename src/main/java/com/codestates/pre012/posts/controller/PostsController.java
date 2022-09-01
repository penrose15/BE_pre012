package com.codestates.pre012.posts.controller;


import com.codestates.pre012.config.oauth.PrincipalDetails;
import com.codestates.pre012.dto.MultiResponseDto;
import com.codestates.pre012.dto.SingleResponseDto;
import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.posts.dto.PostsDto;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.mapper.PostsMapper;
import com.codestates.pre012.posts.service.PostsService;
import com.codestates.pre012.reply.entity.Reply;
import com.codestates.pre012.reply.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/posts")
@Validated // queryParameter 유효성 검증에 필요
public class PostsController {

    private final PostsService postsService;
    private final PostsMapper mapper;
    private final ReplyRepository replyRepository;

    public PostsController(PostsService postsService, PostsMapper mapper, ReplyRepository replyRepository) {
        this.postsService = postsService;
        this.mapper = mapper;
        this.replyRepository = replyRepository;
    }


    @PostMapping("/create")
    public ResponseEntity createPosts(@Valid @RequestBody PostsDto.Post posts,
                                      @AuthenticationPrincipal PrincipalDetails principal) {

        Member member = principal.getMember();
        Posts findPosts = mapper.postsPostDtoToPosts(posts);
        Posts response = postsService.savedPosts(findPosts, member);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postsToPostsDtoResponse(response)), HttpStatus.CREATED);
    }


    @PatchMapping("/patch")
    public ResponseEntity patchPosts(@Valid @RequestBody PostsDto.Patch posts) {

        posts.setPostsId(posts.getPostsId());
        Posts response = postsService.updatePosts(mapper.postsPatchDtoToPosts(posts));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postsToPostsDtoResponse(response)), HttpStatus.OK);
    }

    // 게시글 상세조회
    @GetMapping("/{posts-id}")
    public ResponseEntity viewPosts(@PathVariable("posts-id") @Positive Long postId) {
        // 게시글 내용
        Posts responsePost = postsService.lookPosts(postId);
        long postMemberId = responsePost.getMember().getId();

        // 게시글에 달린 댓글 전부
        List<Reply> replyList = replyRepository.findByPosts_PostsId(postId);
        for (Reply reply : replyList) {
            responsePost.addReplies(reply);
        }

        for (Reply reply : replyList) {
            System.out.println(reply.getContent());
        }

        responsePost.setReplies(replyList);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postsToPostsDtoResponse(responsePost)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findPosts(@RequestParam @Positive int page,
                                    @RequestParam @Positive int size) {

        Page<Posts> pagePosts = postsService.findAllPosts(page - 1, size);

        List<Posts> posts = pagePosts.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.postsToPostsDtoResponses(posts), pagePosts),
                HttpStatus.OK);
    }


    @DeleteMapping("/{posts-Id}")
    public ResponseEntity deletePosts(@PathVariable("posts-Id") @Positive Long postId) {

        postsService.deletePosts(postId);

        return new ResponseEntity<>("삭제 완료", HttpStatus.NO_CONTENT);
    }
}
