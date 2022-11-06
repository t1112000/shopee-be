package com.shopee.service;

import com.shopee.dto.RoleDto;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<ResponseObject> findAll(int page, int pageSize, String name);

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> update(Long id, RoleDto newRole);

    ResponseEntity<ResponseObject> save(RoleDto newRole);

    ResponseEntity<ResponseObject> delete(Long id);
}
