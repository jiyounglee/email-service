package com.challenge.emailservice.service.sender;

import com.challenge.emailservice.data.Email;

public interface EmailSender {
    
    EmailSenderStatus send(final Email email);
}
