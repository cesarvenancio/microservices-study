package com.study.posts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.posts.model.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long>{
}
