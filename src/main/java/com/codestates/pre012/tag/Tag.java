package com.codestates.pre012.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    @Enumerated(value = EnumType.STRING)
    private TagList tagList;

    @OneToMany(mappedBy = "tag")
    private List<Tag_Posts> tag_postsList;

    public void addTag_Posts(Tag_Posts tag_posts) { //양방향 관계 설정을 위해 참조 추가
        tag_postsList.add(tag_posts);
        if(tag_posts.getTag() != this) { //순환 참조
            tag_posts.setTag(this);
        }
    }

    public enum TagList {
        JAVA("Java"), SPRING("Spring"), JAVASCRIPT("Javascript"), NODE_JS("Node.js"), PYTHON("Python"), C1("C"), C2("C++");

        private String name;

        public String getName() {
            return name;
        }

        TagList(String name) {
            this.name = name;
        }
    }

}
