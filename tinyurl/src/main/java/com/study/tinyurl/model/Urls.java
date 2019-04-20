package com.study.tinyurl.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "urls")
public class Urls {
    
    @Id
    private String tiny;
    
    private String longUrl;
    private Date created;
}
