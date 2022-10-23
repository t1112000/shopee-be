package com.shopee.service;

import com.shopee.entity.ResponseObject;
import com.shopee.entity.RoleEntity;
import com.shopee.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<ResponseObject> findAll();

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> update(Long id, RoleEntity newRole);

    ResponseEntity<ResponseObject> save(RoleEntity newRole);

    ResponseEntity<ResponseObject> delete(Long id);
}
