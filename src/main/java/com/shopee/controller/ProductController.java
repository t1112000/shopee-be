package com.shopee.controller;

import com.shopee.dto.ProductDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseObject> findAllProducts(
            @RequestParam(defaultValue = "1") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(0) int pageSize,
            @RequestParam(required = false) String name) {
        return productService.findAll(page, pageSize, name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> saveProduct(@RequestBody @Valid ProductDto newProduct) {
        return productService.save(newProduct);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto newProduct) {
        return productService.update(id, newProduct);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable("id") Long id) {
        return productService.delete(id);
    }
}
