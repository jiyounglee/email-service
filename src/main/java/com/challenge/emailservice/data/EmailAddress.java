package com.challenge.emailservice.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class EmailAddress {

    private Optional<String> name;

    @Email
    @NotNull
    @NotEmpty
    @Size(max = 320)
    private String address;

    public EmailAddress() {
        name = Optional.empty();
    }

    public EmailAddress(@Email @Size(max = 320) @NotNull
                        @NotEmpty String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "EmailAddress{" +
                "name=" + name +
                ", address='" + address + '\'' +
                '}';
    }
}
