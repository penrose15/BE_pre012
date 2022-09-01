package com.codestates.pre012.reply;

import com.codestates.pre012.baseEntity.BaseEntity;
import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.posts.entity.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;




@Setter
@Getter
@Entity
@NoArgsConstructor
public class Reply extends BaseEntity {

    @Id @GeneratedValue
    private long replyId;

    private String content;

    @ManyToOne
    @JoinColumn(name = " postsId")
    private Posts posts;

    //댓글 조회시 member 정보도 같이 띄워야 하므로 FetchType.EAGER 설정
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberId")
    private Member member;

    //setPost()삭제
}
