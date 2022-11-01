package com.shopee.service;

import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface BillService {
    ResponseEntity<ResponseObject> findAll();

    ResponseEntity<ResponseObject> findAllByUserId(Long userId);

    ResponseEntity<ResponseObject> findById(Long id);
}
