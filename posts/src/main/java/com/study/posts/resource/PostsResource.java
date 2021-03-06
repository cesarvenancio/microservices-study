package com.study.posts.resource;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsResource {

	@NotBlank
	@Size(max = 200)
	private String postText;
	
	private Date created;
}
