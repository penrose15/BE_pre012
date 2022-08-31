package com.codestates.pre012.tag;

import com.codestates.pre012.member.entity.Member;
import com.codestates.pre012.tag.converter.TagConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    //tag를 카테고리내에서 선택 가능하게 하면 좋을 것 같아 enum으로 처리하였습니다.
    @Convert(converter = TagConverter.class)
    private String tagName;

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
