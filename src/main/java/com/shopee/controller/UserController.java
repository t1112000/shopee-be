package com.shopee.controller;

import com.shopee.dto.UserDto;
import com.shopee.entity.ResponseObject;
import com.shopee.entity.UserEntity;
import com.shopee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> save(@RequestBody @Valid UserDto user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable("id") Long id, @RequestBody UserEntity user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
