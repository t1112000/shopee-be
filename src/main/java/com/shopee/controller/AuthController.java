package com.shopee.controller;

import com.shopee.dto.ChangePasswordDto;
import com.shopee.dto.SignInDto;
import com.shopee.dto.SignUpDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseObject> signUp(@RequestBody @Valid SignUpDto newUser) {
        return userService.save(newUser);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseObject> signIn(@RequestBody SignInDto user) {
        return userService.signIn(user);
    }

    @PostMapping("/change-password")
    public ResponseEntity<ResponseObject> updatePassword(@RequestBody @Valid ChangePasswordDto user) {
        return userService.updatePassword(user);
    }
}
