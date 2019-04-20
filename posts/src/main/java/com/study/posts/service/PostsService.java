package com.study.posts.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.posts.repository.PostsRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
@Transactional
public class PostsService {

	private PostsRepository postsRepository;

	@Autowired
	public PostsService(PostsRepository postsRepository) {
		this.postsRepository = postsRepository;
	}
	
}