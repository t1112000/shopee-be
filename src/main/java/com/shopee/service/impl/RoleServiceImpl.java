package com.shopee.service.impl;

import com.shopee.dto.RoleDto;
import com.shopee.dto.list.RoleListDto;
import com.shopee.entity.ResponseObject;
import com.shopee.entity.RoleEntity;
import com.shopee.repository.RoleRepository;
import com.shopee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll(int page, int pageSize, String name) {
        Pageable paging = PageRequest.of((page - 1), pageSize);

        List<RoleEntity> roles = roleRepository.findAllByIs_deletedFalse(name, paging).getContent();
        int total = roleRepository.getTotal(name);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query roles successfully", new RoleListDto(total, roles)));
    }

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<RoleEntity> foundRole = roleRepository.findById(id);

        if (foundRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query role successfully", foundRole));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find role with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> update(Long id, RoleDto newRole) {
        Optional<RoleEntity> foundRole = roleRepository.findById(id).map(role -> {
            role.setName(newRole.getName());
            return roleRepository.save(role);
        });

        if (foundRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Updated role successfully", foundRole));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find role with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> save(RoleDto newRole) {
        Optional<RoleEntity> foundRole = roleRepository.findByName(newRole.getName());

        if (foundRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(false, "Invalid role"));
        }

        RoleEntity role = new RoleEntity();
        role.setName(newRole.getName());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Created role successfully", roleRepository.save(role)));
    }

    @Override
    public ResponseEntity<ResponseObject> delete(Long id) {
        Optional<RoleEntity> foundRole = roleRepository.findByIdAndIs_deletedFalse(id).map(role -> {
            role.setIs_deleted(true);
            return roleRepository.save(role);
        });

        if (foundRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Deleted role successfully"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find role with id=" + id));
    }
}
