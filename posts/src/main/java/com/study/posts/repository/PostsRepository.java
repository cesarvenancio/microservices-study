package com.study.posts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.posts.model.Posts;
import com.study.posts.resource.PostsResource;

public interface PostsRepository extends JpaRepository<Posts, Long>{
    
    @Query("SELECT new com.study.posts.resource.PostsResource(u.text, u.created) FROM Posts u")
    public List<PostsResource> getPosts();
    
    @Query("SELECT new com.study.posts.resource.PostsResource(u.text, u.created) FROM Posts u WHERE u.id = :id")
    public PostsResource getPost(@Param("id") Long id);
}
