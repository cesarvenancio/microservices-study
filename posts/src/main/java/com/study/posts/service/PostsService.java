package com.study.posts.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.posts.model.Posts;
import com.study.posts.repository.PostsRepository;
import com.study.posts.resource.PostsResource;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
@Transactional
public class PostsService {

	@Autowired
	private PostsRepository postsRepository;

	public PostsResource createPost(PostsResource postsResource) {
		
	    Posts post = new Posts(null, postsResource.getPostText(), new Date());
	    
	    post = postsRepository.save(post);
	    
	    return new PostsResource(post.getText(), post.getCreated());
	}
	
	public List<PostsResource> getPosts() {
	    return postsRepository.getPosts();
    }
	
	public PostsResource getPost(Long id) {
        return postsRepository.getPost(id);
    }
	
	public void deletePost(Long id) {
        postsRepository.deleteById(id);
    }
}