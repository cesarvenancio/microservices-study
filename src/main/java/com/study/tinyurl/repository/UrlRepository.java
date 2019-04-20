package com.study.tinyurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.tinyurl.model.Urls;

public interface UrlRepository extends JpaRepository<Urls, Long>{
	Urls findByTiny( String tiny );
}
