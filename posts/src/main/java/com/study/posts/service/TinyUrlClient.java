package com.study.posts.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("tinyurl")
public interface TinyUrlClient {

    @GetMapping("/tinyurl/getUrl/{tinyUrl}")
    ResponseEntity<String> getTinyUrl(@PathVariable(value="tinyUrl") String tinyUrl);
}

