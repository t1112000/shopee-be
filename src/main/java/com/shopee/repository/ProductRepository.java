package com.shopee.repository;

import com.shopee.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "select * from products where is_deleted=false", nativeQuery = true)
    List<ProductEntity> findAllByIs_deletedFalse();

    @Query(value = "select * from products where is_deleted=false and product_id= ?1", nativeQuery = true)
    Optional<ProductEntity> findByIdAndIs_deletedFalse(Long id);
}
