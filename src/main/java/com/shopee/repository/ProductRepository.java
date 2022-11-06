package com.shopee.repository;

import com.shopee.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "select * from products where is_deleted=false and lower(name) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    Page<ProductEntity> findAllByIs_deletedFalse(String name, Pageable pageable);

    @Query(value = "select count(*) from products where is_deleted=false and lower(name) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    int getTotal(String name);

    @Query(value = "select * from products where is_deleted=false and product_id= ?1", nativeQuery = true)
    Optional<ProductEntity> findByIdAndIs_deletedFalse(Long id);
}
