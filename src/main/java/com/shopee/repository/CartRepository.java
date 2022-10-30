package com.shopee.repository;

import com.shopee.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Query(value = "select * from carts where is_deleted=false", nativeQuery = true)
    List<CartEntity> findAllByIs_deletedFalse();

    @Query(value = "select * from carts where is_deleted=false AND user_id= ?1", nativeQuery = true)
    Optional<CartEntity> findByIs_deletedFalseAndUserId(Long userId);

    @Query(value = "select * from carts where is_deleted=false and cart_id= ?1", nativeQuery = true)
    Optional<CartEntity> findByIdAndIs_deletedFalse(Long id);
}
