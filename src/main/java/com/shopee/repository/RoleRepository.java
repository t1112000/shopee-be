package com.shopee.repository;

import com.shopee.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    @Query(value = "select * from roles r where r.is_deleted=false",nativeQuery = true)
    List<RoleEntity> findAllByIs_deletedFalse();
}
