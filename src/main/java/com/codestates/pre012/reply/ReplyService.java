package com.codestates.pre012.reply;

import com.codestates.pre012.exception.BusinessLogicException;
import com.codestates.pre012.exception.ExceptionCode;
import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Reply createReply(long postsId,Member member, Reply reply) {

        reply.setPosts(findPost(postsId));
        reply.setMember(member);

        return  replyRepository.save(reply);
    }

    public Reply updateReply(long postsId, Member member,Reply reply) {
        Reply findReply = findReplies(reply.getReplyId());
        Member member1 = findReply.getMember();
        System.out.println(member1.getUsername());

        verifiedMember(member, findReply);

        findReply.setPosts(findPost(postsId));

        Optional.ofNullable(reply.getContent()).ifPresent(findReply::setContent);


        return replyRepository.save(findReply);
    }
    //최신순으로 정렬
    public Page<Reply> getReplies(int page, int size, long postId) {
        findPost(postId).getReplies();
        Page<Reply> replies = replyRepository.findAll(PageRequest.of(page, size, Sort.Direction.DESC,"replyId"));

        return replies;
    }

    public void deleteReply(long postId,Member member,long replyId) {
        Reply reply = findReplies(replyId);

        if(reply.getPosts().getPostsId() !=postId) throw new BusinessLogicException(ExceptionCode.POSTS_NOT_FOUND);

        verifiedMember(member, reply);
        replyRepository.deleteById(replyId);
    }

    private Posts findPost(long postsId) {
        Optional<Posts> posts = postsRepository.findById(postsId);
        Posts findPosts = posts.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POSTS_NOT_FOUND));
        return findPosts;
    }

    private Reply findReplies(long replyId) {
        Optional<Reply> reply = replyRepository.findById(replyId);
        Reply findReply = reply.orElseThrow(() -> new BusinessLogicException(ExceptionCode.REPLY_NOT_FOUND));
        System.out.println(findReply.getContent());

        return findReply;
    }

    private void verifiedMember(Member member, Reply reply) {
        if(!reply.getMember().getUsername().equals(member.getUsername()))
            throw new BusinessLogicException(ExceptionCode.WRONG_MEMBERS_REPLY);
    }
}
