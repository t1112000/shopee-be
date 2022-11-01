package com.shopee.repository;

import com.shopee.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

    @Query(value = "SELECT * FROM bills b,carts c WHERE b.cart_id = c.cart_id AND c.user_id= ?1", nativeQuery = true)
    List<BillEntity> findAllByUserId(Long userId);
}
