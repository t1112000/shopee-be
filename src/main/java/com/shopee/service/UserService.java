package com.shopee.service;

import com.shopee.dto.ChangePasswordDto;
import com.shopee.dto.UserDto;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseObject> findAll();

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> signIn(UserDto user);

    ResponseEntity<ResponseObject> updatePassword(ChangePasswordDto user);

    ResponseEntity<ResponseObject> update(Long id, UserDto newUser);

    ResponseEntity<ResponseObject> save(UserDto newUser);

    ResponseEntity<ResponseObject> delete(Long id);
}
