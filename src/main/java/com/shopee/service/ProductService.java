package com.shopee.service;

import com.shopee.dto.ProductDto;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ResponseObject> findAll(int page, int pageSize, String name);

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> update(Long id, ProductDto newProduct);

    ResponseEntity<ResponseObject> save(ProductDto newProduct);

    ResponseEntity<ResponseObject> delete(Long id);
}
