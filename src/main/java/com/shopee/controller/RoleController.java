package com.shopee.controller;

import com.shopee.dto.RoleDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/roles")
@Tag(name = "Roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<ResponseObject> findAllRoles(
            @RequestParam(defaultValue = "1") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(0) int pageSize,
            @RequestParam(required = false) String name
    ) {
        return roleService.findAll(page, pageSize, name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findRoleById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> saveRole(@RequestBody @Valid RoleDto newRole) {
        return roleService.save(newRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateRole(@PathVariable("id") Long id, @RequestBody @Valid RoleDto newRole) {
        return roleService.update(id, newRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteRole(@PathVariable("id") Long id) {
        return roleService.delete(id);
    }
}
