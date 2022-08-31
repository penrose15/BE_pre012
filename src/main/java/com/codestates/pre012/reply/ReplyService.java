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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostsRepository postsRepository;

    public Reply createReply(long postsId,Member member, Reply reply) {

        reply.setPosts(findPost(postsId));
        reply.setMember(member);

        return  replyRepository.save(reply);
    }

    public Reply updateReply(long postId, Member member,Reply reply) {
        Reply findReply = findReplies(reply.getReplyId());
        verifiedMember(member, reply);
        Optional.ofNullable(reply.getContent()).ifPresent(findReply::setContent);
        findReply.setPosts(findPost(postId));

        return replyRepository.save(findReply);
    }
    //최신순으로 정렬
    public Page<Reply> getReplies(int page, int size, long postId) {
        Page<Reply> replies = replyRepository.findAll(PageRequest.of(page, size, Sort.Direction.DESC,"replyId"));

        return replies;
    }

    public void deleteReply(long postId,Member member,long replyId) {
        Reply reply = findReplies(replyId);

        if(reply.getPosts().getPostsId() !=postId) throw new BusinessLogicException(ExceptionCode.POSTS_NOT_FOUND);

        verifiedMember(member, reply);
        replyRepository.deleteById(replyId);
    }

    private Posts findPost(long postId) {
        Optional<Posts> posts = postsRepository.findById(postId);
        Posts findPosts = posts.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POSTS_NOT_FOUND));
        return findPosts;
    }

    private Reply findReplies(long replyId) {
        Optional<Reply> reply = replyRepository.findById(replyId);
        Reply findReply = reply.orElseThrow(() -> new BusinessLogicException(ExceptionCode.REPLY_NOT_FOUND));

        return findReply;
    }

    private void verifiedMember(Member member, Reply reply) {
        if(!reply.getMember().equals(member)) throw new BusinessLogicException(ExceptionCode.WRONG_MEMBERS_REPLY);
    }
}
