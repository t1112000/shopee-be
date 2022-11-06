package com.shopee.service.impl;

import com.shopee.dto.ProductDto;
import com.shopee.dto.list.ProductListDto;
import com.shopee.entity.CategoryEntity;
import com.shopee.entity.ProductEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.repository.CategoryRepository;
import com.shopee.repository.ProductRepository;
import com.shopee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll(int page, int pageSize, String name) {
        Pageable paging = PageRequest.of((page - 1), pageSize);

        List<ProductEntity> products = productRepository.findAllByIs_deletedFalse(name, paging).getContent();
        int total = productRepository.getTotal(name);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query products successfully", new ProductListDto(total, products)));
    }

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<ProductEntity> foundProduct = productRepository.findById(id);

        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query product successfully", foundProduct));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find product with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> update(Long id, ProductDto newProduct) {
        Optional<ProductEntity> foundProduct = productRepository.findById(id);

        if (foundProduct.isPresent()) {
            if (newProduct.getCategory_id() != null) {
                Optional<CategoryEntity> foundCategory = categoryRepository.findById(newProduct.getCategory_id()).map(category -> {
                    foundProduct.map(product -> {
                        product.setCategory(category);
                        return product;
                    });
                    return category;
                });

                if (foundCategory.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find category width id=" + newProduct.getCategory_id()));
                }
            }

            foundProduct.map(product -> {
                product.setName(newProduct.getName());
                product.setDescription(newProduct.getDescription());
                product.setImages(newProduct.getImages());
                product.setPrice(newProduct.getPrice());
                return productRepository.save(product);
            });


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Updated product successfully", foundProduct));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find product with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> save(ProductDto newProduct) {
        Optional<CategoryEntity> foundCategory = categoryRepository.findById(newProduct.getCategory_id());

        if (foundCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find category width id=" + newProduct.getCategory_id()));
        }

        ProductEntity product = new ProductEntity();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setImages(newProduct.getImages());
        product.setCategory(foundCategory.get());
        product.setPrice(newProduct.getPrice());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Created product successfully", productRepository.save(product)));
    }

    @Override
    public ResponseEntity<ResponseObject> delete(Long id) {
        Optional<ProductEntity> foundProduct = productRepository.findByIdAndIs_deletedFalse(id).map(product -> {
            product.setIs_deleted(true);
            return productRepository.save(product);
        });


        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Deleted product successfully"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find product with id=" + id));
    }
}
