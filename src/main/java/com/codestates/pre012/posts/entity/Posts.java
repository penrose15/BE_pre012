package com.codestates.pre012.posts.entity;

import com.codestates.pre012.member.entity.Member;

import javax.persistence.*;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "title", nullable = false)
    private String title;

    private String test;

    @Column(name = "content", nullable = false)
    private String content;
    
}
