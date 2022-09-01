package com.codestates.pre012.posts.entity;


import com.codestates.pre012.baseEntity.BaseEntity;
import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.reply.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postsId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @JoinColumn(name = "memberId")
    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "posts", fetch = LAZY)
    private List<Reply> replies = new ArrayList<>();

    @Column
    private int view;

}
