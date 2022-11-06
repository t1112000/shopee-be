package com.shopee.controller;

import com.shopee.dto.CategoryDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/categories")
@Tag(name = "Product Categories")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    private ResponseEntity<ResponseObject> getAllCategories(
            @RequestParam(defaultValue = "1") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(0) int pageSize,
            @RequestParam(required = false) String name
    ) {
        return categoryService.findAll(page, pageSize, name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<ResponseObject> findCategoryById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<ResponseObject> updateCategory(@PathVariable("id") Long id, @RequestBody @Valid CategoryDto newCategory) {
        return categoryService.update(id, newCategory);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<ResponseObject> saveCategory(@RequestBody @Valid CategoryDto newCategory) {
        return categoryService.save(newCategory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<ResponseObject> deleteCategory(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }
}
