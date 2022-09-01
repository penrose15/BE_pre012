package com.codestates.pre012.posts.entity;


import com.codestates.pre012.baseEntity.BaseEntity;
import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.reply.Reply;
import com.codestates.pre012.tag.Tag_Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postsId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @JoinColumn(name = "memberId")
    @ManyToOne
    private Member member;


    @OneToMany(mappedBy = "posts",
            cascade = CascadeType.ALL,
            orphanRemoval = true) //posts에 의해 tag의 생명주기가 달려있다고 생각이 들어서 cascade+orphanRemove 처리를 했습니다.
    private List<Tag_Posts> tag_postsList = new ArrayList<>(); //post에 tag조회를 위해 양방향 설정

    public void addTag_Posts(Tag_Posts tag_posts) { //양방향 관계 설정을 위해 참조 추가
        this.tag_postsList.add(tag_posts);
        if(tag_posts.getPosts() != this) { //순환참조 방지
           tag_posts.setPosts(this);
        }
    }

    //page로 댓글을 받지 못해서 일단 임시방편으로 BatchSize로 댓글 100개만 불러올 수 있도록 설정
    //cascadetype.PERSIST : post저장시 댓글도 같이 저장, REMOVE : post 삭제시 댓글다 같이 삭제
    //orphanremoval 고아객체 삭제 https://dev-elop.tistory.com/entry/JPA-orphanRemoval-%EC%9A%A9%EB%8F%84
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "posts",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            orphanRemoval = true) //posts삭제시 reply도 같이 삭제
    @OrderBy("posts_id desc")
    private List<Reply> replies = new ArrayList<>();

    //순환 참조방지에 별 도움이 안되는 것 같아 addReplies() 삭제

    @Column(name = "view")
    private int view;

    private String tags;

    public List<String> tagList(String tag) {
        List<String> list = Arrays.asList(tag.split(" , "));
        return list;
    }



    public Posts(long postsId, String title, String content, int view) {
        this.postsId = postsId;
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public Posts(long postsId, String title, String content) {
        this.postsId = postsId;
        this.title = title;
        this.content = content;
    }

}
