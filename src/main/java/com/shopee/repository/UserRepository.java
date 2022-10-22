package com.shopee.repository;

import com.shopee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query(value = "select * from users u where u.is_deleted=false",nativeQuery = true)
    List<UserEntity> findAllByIs_deletedFalse();
}
