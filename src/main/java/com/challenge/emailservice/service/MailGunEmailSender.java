package com.challenge.emailservice.service;

import com.challenge.emailservice.data.Email;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

public class MailGunEmailSender implements EmailSender {

    private HttpHeaders headers;
    private RestTemplate restTemplate;
    private String url;

    public MailGunEmailSender(final String url, final String apiKey) {
        this.url = url;

        headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + new String(encodeBase64(("api:" + apiKey).getBytes())));

        restTemplate = new RestTemplate();
    }

    @Override
    public void send(Email email) {

        HttpEntity<String> response = restTemplate.exchange(
                buildUrl(email),
                HttpMethod.POST,
                new HttpEntity<>(headers),
                String.class);

        if (((ResponseEntity<String>) response).getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    private String buildUrl(Email email) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("from", "jylee1103@gmail.com")
                .queryParam("to", "jylee1103@gmail.com")
                .queryParam("subject", "test")
                .queryParam("text", "text");
        return builder.toUriString();
    }


}
