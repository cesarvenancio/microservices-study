package com.study.posts.service.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.study.posts.resource.UrlResource;

@FeignClient(name = "tinyurl", fallback = TinyUrlClientFallback.class)
public interface TinyUrlClient {

    @PostMapping("/tinyurl/")
    UrlResource generateTinyUrl(@Valid @RequestBody UrlResource urlResource);
}

