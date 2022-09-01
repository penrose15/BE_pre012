package com.codestates.pre012.tag;

import com.codestates.pre012.tag.dto.TagDto;
import com.codestates.pre012.tag.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag postDtoToTag(TagDto.Post post);
}
