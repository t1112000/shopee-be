package com.shopee.service;

import com.shopee.dto.ChangePasswordDto;
import com.shopee.dto.SignInDto;
import com.shopee.dto.SignUpDto;
import com.shopee.dto.UpdateUserInfoDto;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseObject> findAll(int page, int pageSize, String name);

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> signIn(SignInDto user);

    ResponseEntity<ResponseObject> updatePassword(ChangePasswordDto user);

    ResponseEntity<ResponseObject> update(Long id, UpdateUserInfoDto newUser);

    ResponseEntity<ResponseObject> save(SignUpDto newUser);

    ResponseEntity<ResponseObject> delete(Long id);
}
