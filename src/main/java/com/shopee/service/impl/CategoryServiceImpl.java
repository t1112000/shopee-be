package com.shopee.service.impl;

import com.shopee.dto.CategoryDto;
import com.shopee.entity.CategoryEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.repository.CategoryRepository;
import com.shopee.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query categories successfully", categoryRepository.findAllByIs_deletedFalse()));
    }

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<CategoryEntity> foundCategory = categoryRepository.findById(id);

        if (foundCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query category successfully", foundCategory));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find category with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> update(Long id, CategoryDto newCategory) {
        Optional<CategoryEntity> foundCategory = categoryRepository.findById(id).map(category -> {
            category.setName(newCategory.getName());
            category.setUpdated_at(new Date());
            return categoryRepository.save(category);
        });

        if (foundCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Updated category successfully", foundCategory));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find category with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> save(CategoryDto newCategory) {
        CategoryEntity category = new CategoryEntity();
        category.setName(newCategory.getName());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Created category successfully", categoryRepository.save(category)));
    }

    @Override
    public ResponseEntity<ResponseObject> delete(Long id) {
        Optional<CategoryEntity> foundCategory = categoryRepository.findByIdAndIs_deletedFalse(id).map(category -> {
            category.setIs_deleted(true);
            category.setUpdated_at(new Date());
            return categoryRepository.save(category);
        });


        if (foundCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Deleted category successfully"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find category with id=" + id));
    }
}
