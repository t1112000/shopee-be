package com.shopee.repository;

import com.shopee.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "select * from products p where p.is_deleted=false",nativeQuery = true)
    List<ProductEntity> findAllByIs_deletedFalse();
}
