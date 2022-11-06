package com.shopee.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpDto {
    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "invalid email")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "phone number is required")
    private String phone_number;

    @NotBlank(message = "password is required")
    @Size(min = 6,message = "password should be greater or equals to 6")
    private String password;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
