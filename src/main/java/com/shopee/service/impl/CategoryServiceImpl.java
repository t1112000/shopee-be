package com.shopee.service.impl;

import com.shopee.dto.CategoryDto;
import com.shopee.dto.list.CategoryListDto;
import com.shopee.entity.CategoryEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.repository.CategoryRepository;
import com.shopee.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll(int page, int pageSize, String name) {
        Pageable paging = PageRequest.of((page - 1), pageSize);

        List<CategoryEntity> categories = categoryRepository.findAllByIs_deletedFalse(name, paging).getContent();
        int total = categoryRepository.getTotal(name);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query categories successfully", new CategoryListDto(total, categories)));
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
            return categoryRepository.save(category);
        });


        if (foundCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Deleted category successfully"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find category with id=" + id));
    }
}
