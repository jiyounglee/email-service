package com.challenge.emailservice;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.Response;
import com.challenge.emailservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailServiceController {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity sendEmail(@Valid @RequestBody final Email email) {
        logger.debug("Received request to send email {}", email.toString());
        emailService.sendEmail(email);
        return ResponseEntity.ok().build();
    }
}
