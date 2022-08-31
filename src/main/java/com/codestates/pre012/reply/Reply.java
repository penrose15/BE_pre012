package com.codestates.pre012.reply;

import com.codestates.pre012.baseEntity.BaseEntity;
import com.codestates.pre012.posts.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
public class Reply extends BaseEntity {

    @Id @GeneratedValue
    private long replyId;

    private String content;

    @ManyToOne
    @JoinColumn(name = " postsId")
    private Posts posts;

    public void setPosts(Posts posts) {
        this.posts = posts;
        if(!posts.getReplies().contains(this)) {
            posts.getReplies().add(this);
        }
    }
}
