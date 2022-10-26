package com.shopee.repository;

import com.shopee.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(value = "select * from categories where is_deleted=false", nativeQuery = true)
    List<CategoryEntity> findAllByIs_deletedFalse();

    @Query(value = "select * from categories where is_deleted=false and category_id= ?1", nativeQuery = true)
    Optional<CategoryEntity> findByIdAndIs_deletedFalse(Long id);
}
