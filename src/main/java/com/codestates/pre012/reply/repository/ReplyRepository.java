package com.codestates.pre012.reply.repository;

import com.codestates.pre012.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
