package com.codestates.pre012.tag.converter;

import com.codestates.pre012.tag.entity.Tag;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Objects;
import java.util.stream.Stream;

public class TagConverter implements AttributeConverter<Tag.TagList, String> {

    @Override //enum을 DB에 어떤 값으로 넣을 것인지 정의
    public String convertToDatabaseColumn(Tag.TagList attribute) {
        if(Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getName();
    }

    @Override //DB에 읽힌 값에 따라 어떻게 enum이랑 매칭 시킬 것인지 정의
    public Tag.TagList convertToEntityAttribute(String dbData) {
        if(!StringUtils.hasText(dbData)) {
            return null;
        }
        return Stream.of(Tag.TagList.values())
                .filter(t -> t.getName().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}
