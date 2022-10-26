package com.shopee.controller;

import com.shopee.dto.CategoryDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    private ResponseEntity<ResponseObject> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResponseObject> findCategoryById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ResponseObject> updateCategory(@PathVariable("id") Long id, @RequestBody @Valid CategoryDto newCategory) {
        return categoryService.update(id, newCategory);
    }

    @PostMapping
    private ResponseEntity<ResponseObject> saveCategory(@RequestBody @Valid CategoryDto newCategory) {
        return categoryService.save(newCategory);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseObject> deleteCategory(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }
}
