package com.company.tweetremote.controller;

import com.company.tweetremote.model.UserMessageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;



/**
 * TweetController test class
 * Created by Sunil on 14/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }


    @Test
    public void postMessage() throws Exception {
        UserMessageDTO userMessageDTO = new UserMessageDTO();
        userMessageDTO.setUserLogin("TEST_USER");
        userMessageDTO.setUserLogin("TEST_MESSAGE");
        String userMessageJson = this.json(userMessageDTO);


        mockMvc.perform(post("/tweetremoteserver/postmessage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userMessageJson))
                .andExpect(status().isOk());
    }


    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
