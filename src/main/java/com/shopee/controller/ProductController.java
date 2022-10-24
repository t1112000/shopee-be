package com.shopee.controller;

import com.shopee.entity.ProductEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> save(@RequestBody @Valid ProductEntity product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable("id") Long id, @RequestBody ProductEntity product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
