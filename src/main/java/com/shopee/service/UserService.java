package com.shopee.service;

import com.shopee.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    UserEntity update(Long id,UserEntity user);

    UserEntity save(UserEntity user);

    void delete(Long id);
}
