package com.shopee.repository;

import com.shopee.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query(value = "select * from roles where is_deleted=false and lower(name) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    Page<RoleEntity> findAllByIs_deletedFalse(String name, Pageable pageable);

    @Query(value = "select count(*) from roles where is_deleted=false and lower(name) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    int getTotal(String name);

    @Query(value = "select * from roles where name= ?1", nativeQuery = true)
    Optional<RoleEntity> findByName(String name);

    @Query(value = "select * from roles where is_deleted=false and role_id= ?1", nativeQuery = true)
    Optional<RoleEntity> findByIdAndIs_deletedFalse(Long id);
}
