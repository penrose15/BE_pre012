package com.codestates.pre012.tag.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    @Enumerated(value = EnumType.STRING)
    private TagList tagList;

    public enum TagList {
        Java, Spring, C, NodeJs, Python, MySql;
    }

    public Tag(TagList tagList) {
        this.tagList = tagList;
    }
}
