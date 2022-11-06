package com.shopee.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInDto {
    @Email(message = "invalid email")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6,message = "password should be greater or equals to 6")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
