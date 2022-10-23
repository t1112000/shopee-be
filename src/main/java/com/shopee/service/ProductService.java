package com.shopee.service;

import com.shopee.entity.ProductEntity;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ResponseObject> findAll();

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> update(Long id, ProductEntity newProduct);

    ResponseEntity<ResponseObject> save(ProductEntity newProduct);

    ResponseEntity<ResponseObject> delete(Long id);
}
