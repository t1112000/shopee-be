package com.shopee.controller;

import com.shopee.dto.UpdateUserInfoDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> findAllUsers(
            @RequestParam(defaultValue = "1") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(0) int pageSize,
            @RequestParam(required = false) String name
    ) {
        return userService.findAll(page, pageSize, name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserInfoDto newUser) {
        return userService.update(id, newUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
