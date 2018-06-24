package com.challenge.emailservice;

import com.challenge.emailservice.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@WebMvcTest(EmailServiceController.class)
public class EmailServiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmailService service;

    @Test
    public void shouldReturnErrorWhenRequestedWithInvalidEmailPayload() throws Exception {
        mvc.perform(post("/email").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}