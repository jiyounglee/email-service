package com.challenge.emailservice.service;

import com.challenge.emailservice.data.Email;

public interface EmailSender {
    void send(final Email email);
}
