package com.shopee.service.impl;

import com.shopee.entity.ResponseObject;
import com.shopee.entity.RoleEntity;
import com.shopee.repository.RoleRepository;
import com.shopee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Query roles successfully",roleRepository.findAllByIs_deletedFalse()));
    }

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<RoleEntity> foundRole = roleRepository.findById(id);

        if(foundRole.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query role successfully", foundRole));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find role with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> update(Long id, RoleEntity newRole) {
        Optional<RoleEntity> foundRole = roleRepository.findById(id).map(role->{
            role.setName(newRole.getName());
            role.setUpdated_at(new Date());
            return roleRepository.save(role);
        });

        if(foundRole.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Updated role successfully", foundRole));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find role with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> save(RoleEntity newRole) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Created role successfully", roleRepository.save(newRole)));
    }

    @Override
    public ResponseEntity<ResponseObject> delete(Long id) {
        Optional<RoleEntity> foundRole = roleRepository.findById(id).map(role->{
            role.setIs_deleted(true);
            role.setUpdated_at(new Date());
            return roleRepository.save(role);
        });

        if(foundRole.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Deleted role successfully"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find role with id=" + id));
    }
}
