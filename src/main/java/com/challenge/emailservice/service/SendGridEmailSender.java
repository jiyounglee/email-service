package com.challenge.emailservice.service;

import com.challenge.emailservice.data.Email;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.POST;

public class SendGridEmailSender implements EmailSender {

    private String url;
    private RestTemplate restTemplate;
    private HttpHeaders headers;


    public SendGridEmailSender(final String url, final String apiKey) {
        this.url = url;
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void send(Email email) {
        HttpEntity<String> response = restTemplate.exchange(
                url,
                POST,
                new HttpEntity(createPayload(email), headers),
                String.class);
        if (((ResponseEntity<String>) response).getStatusCode() != HttpStatus.ACCEPTED) {
            throw new RuntimeException();
        }
    }

    private String createPayload(Email email) {
        return "{\"personalizations\":[{\"to\":[{\"email\":\"jylee1103@gmail.com\"}]}],\"from\":{\"email\":\"jylee1103@gmail.com\"},\"subject\":\"Sending with SendGrid is Fun\",\"content\":[{\"type\":\"text/plain\",\"value\":\"and easy to do anywhere, even with cURL\"}]}";
    }
}
