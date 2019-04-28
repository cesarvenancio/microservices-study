package com.study.posts.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.study.posts.resource.PostsResource;
import com.study.posts.resource.UrlResource;
import com.study.posts.service.PostsService;
import com.study.posts.service.TinyUrlClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostsControllerTest {

	@Autowired
	private PostsService postsService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TinyUrlClient tinyUrlClient;

	@Test
	public void givenPostExist_whenPostIsDeleted_thenNoContentIsReceived() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/{id}", "1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void givenPostDoesNotExist_whenPostIsDeleted_thenNotFoundIsReceived() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/{id}", "99").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void givenPost_whenPostIsRetrieved_thenPostIsReceived() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/{id}", "2").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.postText").value("test2")).andExpect(status().isOk());
	}

	@Test
	public void givenPostDoesNotExist_whenPostIsRetrieved_thenNotFoundIsReceived() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/{id}", "98").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void givenCreatePost_whenPostIsCreated_then200IsReceivedAndJson() throws Exception {
		PostsResource postResource = new PostsResource("TEST", null);

		mockMvc.perform(MockMvcRequestBuilders.post("/").content("{\"postText\" : \"TEST\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.postText").value(postResource.getPostText()));
	}

	@Test
	public void givenCreatePost_whenPostIsCreatedAndTextIsBlank_thenExceptionIsReceived() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/").content("{\"postText\" : \"\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void givenTinyUrlClient_whenGetTinyUrlIsCalled_thenReceivedATinyUrl() throws Exception {
		assertEquals("http://www.google.com", tinyUrlClient.getTinyUrl("aaxx").getBody());
	}

}
