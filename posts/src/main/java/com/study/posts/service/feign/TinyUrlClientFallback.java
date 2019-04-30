package com.study.posts.service.feign;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.study.posts.resource.UrlResource;

@Component
public class TinyUrlClientFallback implements TinyUrlClient{

    @Override
    public UrlResource generateTinyUrl(@Valid UrlResource urlResource) {
        return urlResource;
    }

}
