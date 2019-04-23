package com.study.posts.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.posts.resource.PostsResource;
import com.study.posts.service.PostsService;

@RestController
public class PostsController {

    private PostsService postsService;
    
    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }
    
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }  
    
    @PostMapping(value = "/")
    public PostsResource createPost(@Valid @RequestBody PostsResource postsResource) {
        return postsService.createPost(postsResource);
    }
    
    @GetMapping(value = "/")
    public List<PostsResource> getPosts() {
        return postsService.getPosts();
    }
    
    @GetMapping(value = "/{id}")
    public PostsResource getPost(@PathVariable(value="id") Long id) {
        return postsService.getPost(id);
    }
    
    @DeleteMapping(value = "/}")
    public ResponseEntity<PostsResource> deletePost(@PathVariable(value="id") Long id) {
        if (postsService.getPost(id) == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
 
        postsService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
