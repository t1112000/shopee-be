package com.shopee.repository;

import com.shopee.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select * from users where is_deleted=false and lower(name) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    Page<UserEntity> findAllByIs_deletedFalse(String name, Pageable pageable);

    @Query(value = "select count(*) from users where is_deleted=false and lower(name) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    int getTotal(String name);

    @Query(value = "select * from users where email= ?1", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "select * from users where is_deleted=false and user_id= ?1", nativeQuery = true)
    Optional<UserEntity> findByIdAndIs_deletedFalse(Long id);
}
