package com.shopee.repository;

import com.shopee.entity.BillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

    @Query(value = "SELECT * FROM bills WHERE user_id= ?1", nativeQuery = true)
    Page<BillEntity> findAllByUserId(Pageable pageable, Long userId);
}
