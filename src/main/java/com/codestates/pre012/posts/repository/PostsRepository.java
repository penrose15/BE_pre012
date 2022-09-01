package com.codestates.pre012.posts.repository;


import com.codestates.pre012.posts.entity.Posts;
<<<<<<< HEAD
=======
>>>>>>> 0ce775b3b0ba87e0a6305a80a810714fe960692f
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

<<<<<<< HEAD
=======
>>>>>>> 0ce775b3b0ba87e0a6305a80a810714fe960692f

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.postsId = :id")
    int updateView(Long id);

//    @Query()
//    Optional<Posts> findPosts(long postsId);

}
