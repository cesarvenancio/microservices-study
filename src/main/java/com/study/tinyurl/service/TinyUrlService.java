package com.study.tinyurl.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.study.tinyurl.model.Urls;
import com.study.tinyurl.repository.UrlRepository;
import com.study.tinyurl.util.UrlShortner;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
@Transactional
public class TinyUrlService {

	private UrlRepository urlRepository;
    private static AtomicLong urlCounter = new AtomicLong(99999);

	@Autowired
	public TinyUrlService(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}
	
    private static Map<String, Urls> urls = new HashMap<>();
    private static final String DOMAIN = "localhost:9090/tinyurl/";
    
    public String shortUrl(String longURL) {
        long counter = urlCounter.getAndIncrement();

        String tiny = UrlShortner.getBase62From10(counter);
        Urls url = new Urls(tiny, longURL, new Date());
        urls.put(tiny, url);
        urlRepository.save(url);
        
        tiny = DOMAIN+tiny;
        return tiny;
    }

    @Cacheable(value = "url", unless="#result == null")
    public Urls getLongUrl(String tiny) {
    	
    	if(Objects.isNull(urls.get(tiny))){
    		return urlRepository.findByTiny(tiny);
    	}
    	
        return urls.get(tiny);
    }
}