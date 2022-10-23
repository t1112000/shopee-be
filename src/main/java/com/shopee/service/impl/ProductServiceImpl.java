package com.shopee.service.impl;

import com.shopee.entity.ProductEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.repository.ProductRepository;
import com.shopee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Query products successfully",productRepository.findAllByIs_deletedFalse()));
    }

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<ProductEntity> foundProduct = productRepository.findById(id);

        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query product successfully", foundProduct));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find product with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> update(Long id, ProductEntity newProduct) {
        Optional<ProductEntity> foundProduct = productRepository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setImages(newProduct.getImages());
            product.setDescription(newProduct.getDescription());
            product.setPrice(newProduct.getPrice());
            product.setUpdated_at(new Date());
            product.setCategory(newProduct.getCategory());
            return productRepository.save(product);
        });

        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Updated product successfully", foundProduct));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find product with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> save(ProductEntity newProduct) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Created product successfully",productRepository.save(newProduct)));
    }

    @Override
    public ResponseEntity<ResponseObject> delete(Long id) {
        Optional<ProductEntity> foundProduct = productRepository.findById(id).map(product->{
            product.setIs_deleted(true);
            product.setUpdated_at(new Date());
            return productRepository.save(product);
        });

        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Deleted product successfully"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find product with id=" + id));
    }
}
