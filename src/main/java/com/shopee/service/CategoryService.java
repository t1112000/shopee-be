package com.shopee.service;

import com.shopee.dto.CategoryDto;
import com.shopee.entity.CategoryEntity;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<ResponseObject> findAll();

    ResponseEntity<ResponseObject> findById(Long id);

    ResponseEntity<ResponseObject> update(Long id, CategoryDto newCategory);

    ResponseEntity<ResponseObject> save(CategoryDto newCategory);

    ResponseEntity<ResponseObject> delete(Long id);
}
