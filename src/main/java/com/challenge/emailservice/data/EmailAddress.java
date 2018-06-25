package com.challenge.emailservice.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Optional;

public class EmailAddress {

    private Optional<String> name;

    @Email
    @Size(max = 320)
    private String address;

    public EmailAddress() {
        name = Optional.empty();
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
