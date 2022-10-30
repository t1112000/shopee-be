package com.shopee.service;

import com.shopee.dto.CartDto;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<ResponseObject> findAll();

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> findByUserId(Long userId);

    ResponseEntity<ResponseObject> save(CartDto cartDto);

    ResponseEntity<ResponseObject> update(Long id, CartDto cartDto);

    ResponseEntity<ResponseObject> delete(Long id);
}
