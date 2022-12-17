package com.shopee.service;

import com.shopee.dto.CartDto;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<ResponseObject> findAll(int page, int pageSize);

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> findByUserId(Long userId);

    ResponseEntity<ResponseObject> save(Long userId, CartDto cartDto);

    ResponseEntity<ResponseObject> update(Long userId, CartDto cartDto);

    ResponseEntity<ResponseObject> delete(Long id);
}
