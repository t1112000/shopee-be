package com.shopee.controller;

import com.shopee.entity.UserEntity;
import com.shopee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserEntity save(@RequestBody UserEntity user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable("id") Long id, @RequestBody UserEntity user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
