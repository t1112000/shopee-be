package com.shopee.repository;

import com.shopee.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    @Query(value = "select * from carts where product_id= ?1", nativeQuery = true)
    Optional<CartItemEntity> findByProductId(Long productId);
}
