package com.codestates.pre012.reply.repository;

import com.codestates.pre012.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByPosts_PostsId(Long id);
}
