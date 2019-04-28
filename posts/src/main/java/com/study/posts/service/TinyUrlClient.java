package com.study.posts.service;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.study.posts.resource.UrlResource;

@FeignClient("tinyurl")
public interface TinyUrlClient {

    @PostMapping("/tinyurl/")
    UrlResource generateTinyUrl(@Valid @RequestBody UrlResource urlResource);

    @GetMapping("/tinyurl/getUrl/{tinyUrl}")
    ResponseEntity<String> getTinyUrl(String tinyUrl);
}

