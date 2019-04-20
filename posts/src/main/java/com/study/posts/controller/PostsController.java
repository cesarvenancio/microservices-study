package com.study.posts.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.posts.resource.PostsResource;

@RestController
public class PostsController {

    @PostMapping(value = "/")
    public void insertPost(@Valid @RequestBody PostsResource postsResource) {
    }
    
}
