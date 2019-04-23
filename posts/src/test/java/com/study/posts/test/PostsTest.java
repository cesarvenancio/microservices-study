package com.study.posts.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.study.posts.controller.PostsController;
import com.study.posts.service.PostsService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostsTest {

    protected MockMvc mvc;
    
    @InjectMocks
    private PostsController postsController;

    @Mock
    private PostsService postsService;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(postsController)
                .build();
    }
    
    @Test
    public void givenPostDoesNotExist_whenPostIsRetrieved_then404IsReceived() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/foo/{id}", "11")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
}
