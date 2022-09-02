package com.codestates.pre012.posts.mapper;


import com.codestates.pre012.posts.dto.PostsDto;
import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.reply.dto.ReplyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostsMapper {

    Posts postsPostDtoToPosts(PostsDto.Post requestBody);
    PostsDto.PostsResponse postsToPostsResponse(Posts posts);
    PostsDto.SearchResponse postsToSearchResponse(Posts posts);
    List<PostsDto.PostsResponse> postsToPostsDtoResponses(List<Posts> posts);

}
