package com.study.posts.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("tinyurl")
public interface TinyUrlClient {

    @GetMapping("/getUrl/{tinyUrl}")
    String getTinyUrl(String tinyUrl);
}
