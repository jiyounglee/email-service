package com.challenge.emailservice;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.EmailAddress;
import com.challenge.emailservice.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnErrorWhenRequestedWithNoPayload() throws Exception {
        mvc.perform(post("/email").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithNoFrom() throws Exception {
        Email email = new Email();
        email.setSubject("Some Subject");
        email.setContent("Some Content");
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithInvalidFrom() throws Exception {
        Email email = new Email();
        email.setFrom(new EmailAddress("invalidEmail"));
        email.setSubject("Some Subject");
        email.setContent("Some Content");
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithEmptyFrom() throws Exception {
        Email email = new Email();
        email.setFrom(new EmailAddress());
        email.setSubject("Some Subject");
        email.setContent("Some Content");
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithOnlyNameFrom() throws Exception {
        EmailAddress from = new EmailAddress();
        from.setName(Optional.of("FROM"));

        Email email = new Email();
        email.setFrom(from);
        email.setSubject("Some Subject");
        email.setContent("Some Content");
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithEmptyTo() throws Exception {
        Email email = new Email();
        email.setSubject("Some Subject");
        email.setContent("Some Content");
        email.setFrom(new EmailAddress("to@email.com"));
        email.setTo(Lists.newArrayList());

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithNullSubject() throws Exception {
        Email email = new Email();
        email.setContent("Some Content");
        email.setFrom(new EmailAddress("to@email.com"));
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithEmptySubject() throws Exception {
        Email email = new Email();
        email.setSubject("");
        email.setContent("Some Content");
        email.setFrom(new EmailAddress("to@email.com"));
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithSubjectExeedingMaxSize() throws Exception {
        Email email = new Email();
        email.setSubject("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In amet.");
        email.setContent("Some Content");
        email.setFrom(new EmailAddress("to@email.com"));
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithNullContent() throws Exception {
        Email email = new Email();
        email.setSubject("Some Subject");
        email.setFrom(new EmailAddress("to@email.com"));
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithEmptyContent() throws Exception {
        Email email = new Email();
        email.setContent("");
        email.setSubject("Some Subject");
        email.setFrom(new EmailAddress("to@email.com"));
        email.setTo(Lists.newArrayList(new EmailAddress("to@email.com")));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnErrorWhenRequestedWithExceedingNumOfRecipients() throws Exception {
        List<EmailAddress> to = new ArrayList<>();
        List<EmailAddress> cc = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            to.add(new EmailAddress("to" + i + "@email.com"));
            cc.add(new EmailAddress("cc" + i + "@email.com"));
        }

        Email email = new Email();
        email.setContent("");
        email.setSubject("Some Subject");
        email.setFrom(new EmailAddress("to@email.com"));
        email.setTo(to);
        email.setCc(Optional.of(cc));
        email.setBcc(Optional.of(Lists.newArrayList(new EmailAddress("bcc@email.com"))));

        mvc.perform(
                post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().is4xxClientError());
    }
}