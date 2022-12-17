package com.shopee.controller;

import com.shopee.entity.ResponseObject;
import com.shopee.service.BillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/bills")
@Tag(name = "Bills")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> findAllBills(
            @RequestParam(defaultValue = "1") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(0) int pageSize
    ) {
        return billService.findAll(page, pageSize);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<ResponseObject> findAllBillsByUserId(
            @RequestParam(defaultValue = "1") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(0) int pageSize,
            @PathVariable("user_id") Long userId) {
        return billService.findAllByUserId(page, pageSize, userId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> findBillById(@PathVariable("id") Long id) {
        return billService.findById(id);
    }
}
