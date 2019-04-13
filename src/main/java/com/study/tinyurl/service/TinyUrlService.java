package com.study.tinyurl.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.study.tinyurl.util.UrlShortner;

@Service
public class TinyUrlService {

    private static Map<String, String> urls = new HashMap<>();
    private static final String DOMAIN = "localhost:9090/tinyurl/";
    
    public String shortUrl(String longURL) {
        String shortURL = UrlShortner.getBase62From10(0l);
        urls.put(shortURL, longURL);
        shortURL = DOMAIN+shortURL;

        return shortURL;
    }

    @Cacheable(value = "url", unless="#result == null")
    public String getLongUrl(String shortUrl) {
        return urls.get(shortUrl);
    }
}