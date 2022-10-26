package com.shopee.repository;

import com.shopee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select * from users where is_deleted=false", nativeQuery = true)
    List<UserEntity> findAllByIs_deletedFalse();

    @Query(value = "select * from users where email= ?1", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "select * from users where is_deleted=false and user_id= ?1", nativeQuery = true)
    Optional<UserEntity> findByIdAndIs_deletedFalse(Long id);
}
