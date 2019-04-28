package com.study.tinyurl.controller;

import java.net.URI;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.tinyurl.resource.UrlResource;
import com.study.tinyurl.service.TinyUrlService;

@RestController
@Validated
public class TinyUrlController {

    @Autowired
    private TinyUrlService tinyUrlService;
    
    @PostMapping(value = "/")
    public UrlResource generateTinyUrl(@Valid @RequestBody UrlResource urlResource) {
        return new UrlResource(tinyUrlService.shortUrl(urlResource.getUrl()));
    }
    
    @GetMapping("{tinyUrl}")
    public ResponseEntity<String> tinyRedirect(@PathVariable String tinyUrl, HttpServletResponse response) {
        UrlResource url = tinyUrlService.getLongUrl(tinyUrl);
    	if(Objects.isNull(url)) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setLocation(URI.create(url.getUrl()));
    	
    	return new ResponseEntity<>(headers, HttpStatus.TEMPORARY_REDIRECT);
    }
    
    @GetMapping("/getUrl/{tinyUrl}")
    public ResponseEntity<String> getUrlFromTiny(@PathVariable(value="tinyUrl") String shortUrl) {
        UrlResource url = tinyUrlService.getLongUrl(shortUrl);
    	if(Objects.isNull(url)) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<>(url.getUrl(), HttpStatus.OK);
    }
    
}
