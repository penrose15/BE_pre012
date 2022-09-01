package com.codestates.pre012.tag;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag postDtoToTag(TagDto.Post post);
}
