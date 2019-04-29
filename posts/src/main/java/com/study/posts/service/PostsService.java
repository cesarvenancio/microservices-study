package com.study.posts.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.posts.model.Posts;
import com.study.posts.repository.PostsRepository;
import com.study.posts.resource.PostsResource;
import com.study.posts.resource.UrlResource;

@Service
@Transactional
public class PostsService {

	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
    private TinyUrlClient tinyUrlClient;

	public PostsResource createPost(PostsResource postsResource) {
		
	    Posts post = new Posts(null, tinyUrlsFromText(postsResource.getPostText()), new Date());
	    
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
	
	private String tinyUrlsFromText(String text) {
	    
	    UrlValidator urlValidator = new UrlValidator();
	    
	    String [] words = text.split(" ");
	    
	    for (String word : words) {
	        if(urlValidator.isValid(word)) {
	            String shortUrl = tinyUrlClient.generateTinyUrl(new UrlResource(word)).getUrl();
	            text = text.replace(word, shortUrl);
	        }
        }
	    
	    return text;
	}
	
}