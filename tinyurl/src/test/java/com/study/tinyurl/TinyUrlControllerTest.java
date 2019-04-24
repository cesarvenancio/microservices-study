package com.study.tinyurl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.study.tinyurl.service.TinyUrlService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TinyUrlControllerTest {

    @Autowired
    private TinyUrlService tinyUrlService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @TestConfiguration
    static class TestContextConfiguration {
       @Bean
       public MethodValidationPostProcessor bean() {
          return new MethodValidationPostProcessor();
       }
    }
    
    @Test
    public void givenLongUrl_whenTinyUrlIsCalled_thenReceiveTinyUrl() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.post("/")
                .content("{\"url\" : \"http://www.google.com\"}")
                .contentType(MediaType.APPLICATION_JSON)
    	        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.url").exists())          
                .andExpect(status().isOk());
    }
    
    @Test
    public void givenTinyUrl_whenLongUrlServiceIsCalled_thenReceiveLongUrl() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/getUrl/{tinyUrl}", "aaxx")
                .accept(MediaType.APPLICATION_JSON))
    	.andExpect(content().string("http://www.google.com"))          
    	.andExpect(status().isOk());
    }
    
    @Test
    public void givenNotExistentTinyUrl_whenLongUrlServiceIsCalled_thenReceiveNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getUrl/{tinyUrl}", "bbaa")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }
    
    @Test
    public void givenTinyUrl_whenTinyUrlIsCalled_thenRedirectToLongUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/{tinyUrl}" , "aaxx")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://www.google.com"))          
                .andExpect(status().isTemporaryRedirect());
    }
    
}
