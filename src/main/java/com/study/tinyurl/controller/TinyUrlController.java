package com.study.tinyurl.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.tinyurl.resource.UrlResource;
import com.study.tinyurl.service.TinyUrlService;

@RestController
public class TinyUrlController {

    @Autowired
    private TinyUrlService tinyUrlService;
    
    @GetMapping("{tinyUrl}")
    public void tinyRedirect(@PathVariable String tinyUrl, HttpServletResponse response) {
        response.setHeader("Location", tinyUrlService.getLongUrl(tinyUrl));
        response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
    }
    
    @PostMapping(value = "/")
    public UrlResource generateTinyUrl(@Valid @RequestBody UrlResource urlResource) {
        return new UrlResource(tinyUrlService.shortUrl(urlResource.getUrl()));
    }
    
    @GetMapping("/getUrl/{shortUrl}")
    public String getUrlFromTiny(@PathVariable(value="shortUrl") String shortUrl) {
        return tinyUrlService.getLongUrl(shortUrl);
    }
    
}
