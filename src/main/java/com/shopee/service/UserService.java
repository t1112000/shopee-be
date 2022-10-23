package com.shopee.service;

import com.shopee.entity.ResponseObject;
import com.shopee.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    ResponseEntity<ResponseObject> findAll();

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> update(Long id, UserEntity newUser);

    ResponseEntity<ResponseObject> save(UserEntity newUser);

    ResponseEntity<ResponseObject> delete(Long id);
}
