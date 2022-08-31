package com.codestates.pre012.tag;

import com.codestates.pre012.posts.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Tag_Posts {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tag_postsId;

    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;

    public void setTag(Tag tag) { //양방향 관계 설정을 위해 추가
        this.tag = tag;
        if(!tag.getTag_postsList().contains(this)) { //순환 참조 방지
            tag.getTag_postsList().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "postId")
    private Posts posts;

    public void setPosts(Posts posts) { //양방향 관계설정을 위해 추가
        this.posts = posts;
        if(!posts.getTag_postsList().contains(this)) { //순환 참조 방지
            posts.getTag_postsList().add(this);
        }
    }
}
