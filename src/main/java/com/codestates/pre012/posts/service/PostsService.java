package com.codestates.pre012.posts.service;

import com.codestates.pre012.exception.BusinessLogicException;
import com.codestates.pre012.exception.ExceptionCode;
import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.repository.PostsRepository;
import com.codestates.pre012.reply.entity.Reply;
import com.codestates.pre012.reply.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostsService {

    private final PostsRepository postsRepository;

    private final ReplyRepository replyRepository;

    public PostsService(PostsRepository postsRepository, ReplyRepository replyRepository) {
        this.postsRepository = postsRepository;
        this.replyRepository = replyRepository;
    }


    public Posts savedPosts(Posts postsPost, Member member) {
        postsPost.setMember(member);
        return postsRepository.save(postsPost);
    }

    public Posts updatePosts(Posts patchPost) {

        Posts findPosts = existPosts(patchPost.getPostsId());

        Optional.ofNullable(patchPost.getTitle())
                .ifPresent(findPosts::setTitle);
        Optional.ofNullable(patchPost.getContent())
                .ifPresent(findPosts::setContent);

        return postsRepository.save(findPosts);
    }

    public Posts lookPosts(long postId) {
        Posts posts = postsRepository.findById(postId).orElse(null);
        int count = postsRepository.updateView(postId);
        return existPosts(postId);
    }

    public Page<Posts> findAllPosts(int page, int size) {
        return postsRepository.findAll(PageRequest.of(page, size, Sort.by("postsId").descending()));
    }

    public void deletePosts(long postId) {

        Posts findPosts = existPosts(postId);

        postsRepository.delete(findPosts);
    }

    private Posts existPosts(long postsId) {

        Optional<Posts> existPosts = postsRepository.findById(postsId);

        return existPosts.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POSTS_NOT_FOUND));
    }
}
