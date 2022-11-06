package com.shopee.repository;

import com.shopee.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(value = "select * from categories where is_deleted=false and lower(name) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    Page<CategoryEntity> findAllByIs_deletedFalse(String name, Pageable pageable);

    @Query(value = "select count(*) from categories where is_deleted=false and lower(name) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    int getTotal(String name);

    @Query(value = "select * from categories where is_deleted=false and category_id= ?1", nativeQuery = true)
    Optional<CategoryEntity> findByIdAndIs_deletedFalse(Long id);
}
