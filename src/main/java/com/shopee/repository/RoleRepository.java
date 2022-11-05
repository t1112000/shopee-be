package com.shopee.repository;

import com.shopee.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query(value = "select * from roles where is_deleted=false", nativeQuery = true)
    List<RoleEntity> findAllByIs_deletedFalse();

    @Query(value = "select * from roles where name= ?1", nativeQuery = true)
    Optional<RoleEntity> findByName(String name);

    @Query(value = "select * from roles where is_deleted=false and role_id= ?1", nativeQuery = true)
    Optional<RoleEntity> findByIdAndIs_deletedFalse(Long id);
}
