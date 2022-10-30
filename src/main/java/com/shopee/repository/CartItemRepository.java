package com.shopee.repository;

import com.shopee.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    @Query(value = "select * from cart_item where product_id= ?1", nativeQuery = true)
    List<CartItemEntity> findAllByProductId(Long productId);
}
