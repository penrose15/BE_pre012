package com.codestates.pre012.posts.service;

import com.codestates.pre012.exception.BusinessLogicException;
import com.codestates.pre012.exception.ExceptionCode;
import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.repository.PostsRepository;

import com.codestates.pre012.reply.repository.ReplyRepository;

import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.entity.TagPosts;
import com.codestates.pre012.tag.service.TagPostService;
import com.codestates.pre012.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    private final ReplyRepository replyRepository;
    private final TagPostService tagPostService;
    private final TagService tagService;


    public Posts savedPosts(Posts postsPost, Member member, List<Tag> tag) {

        postsPost.setMember(member);
        Posts posts = postsRepository.save(postsPost);
        List<TagPosts> list = new ArrayList<>();

        for(int i = 0; i< tag.size(); i++) {
            tag.set(i ,tagService.saveOrFindTag(tag.get(i)));
        }
        for(int i = 0; i< tag.size(); i++) {
            list.add(tagPostService.saveTagPosts(posts, new TagPosts(), tag.get(i)));
            posts.setTagPosts(list);
        }
        return postsRepository.save(posts);
    }

    public Posts updatePosts(Long postsId,Posts posts ,Member member, List<Tag> tags) {


        Posts findPosts = existPosts(postsId);

        if(!findPosts.getMember().getPassword().equals(member.getPassword())) throw new RuntimeException("자신의 글만 수정 가능합니다.");

        Optional.ofNullable(posts.getTitle())
                .ifPresent(findPosts::setTitle);
        Optional.ofNullable(posts.getContent())
                .ifPresent(findPosts::setContent);

        if (tags != null && !tags.isEmpty()) {
            List<TagPosts> tagPosts = findPosts.getTagPosts();
            for (int i = 0; i < tagPosts.size(); i++) {
                tagPosts.set(i, tagPostService.updateTagPosts(tagPosts.get(i), tags.get(i)));
            }

            findPosts.setTagPosts(tagPosts);
        }
        return postsRepository.save(findPosts);


    }

    public Posts lookPosts(long postId) {

        Posts posts = existPosts(postId);
        String username = posts.getMember().getUsername();
        System.out.println("==============================================" + username);

        int count = postsRepository.updateView(postId);

        return posts;
    }

    public Page<Posts> findAllPosts(int page, int size) {
        return postsRepository.findAll(PageRequest.of(page, size, Sort.by("postsId").descending()));
    }


    public void deletePosts(long postId, Member member) {

        Posts findPosts = existPosts(postId);
        if(findPosts.getMember().getPassword().equals(member.getPassword())) {
            postsRepository.delete(findPosts);
        }
        else throw new RuntimeException("자신의 게시글만 삭제 가능합니다.");
    }


    private Posts existPosts(long postsId) {

        Optional<Posts> existPosts = postsRepository.findById(postsId);

        return existPosts.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POSTS_NOT_FOUND));
    }


}
