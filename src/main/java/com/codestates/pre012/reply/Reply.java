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
    @JoinColumn(name = "postsId")
    private Posts posts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberId")
    private Member member;

    public void setPosts(Posts posts) {
        this.posts = posts;
        if(!posts.getReplies().contains(this)) {
            posts.getReplies().add(this);
        }
    }
}
