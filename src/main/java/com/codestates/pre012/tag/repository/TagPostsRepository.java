package com.codestates.pre012.tag.repository;

import com.codestates.pre012.posts.entity.Posts;
import com.codestates.pre012.tag.entity.Tag;
import com.codestates.pre012.tag.entity.TagPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagPostsRepository extends JpaRepository<TagPosts, Long> {

    @Query("select t from TagPosts t where t.posts.postsId = :postsId")
    Optional<TagPosts> findByPostsPostsId(Long postsId);

    @Query("select t.posts from TagPosts t where t.tag.tagList = :tagList")
    List<Posts> findPostsByTag(Tag.TagList tagList);
}
