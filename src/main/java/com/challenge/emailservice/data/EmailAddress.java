package com.challenge.emailservice.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class EmailAddress {

    private String name;

    @Email
    @Size(max = 320)
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
