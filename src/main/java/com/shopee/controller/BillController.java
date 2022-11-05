package com.shopee.controller;

import com.shopee.entity.ResponseObject;
import com.shopee.service.BillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bills")
@Tag(name="Bills")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<ResponseObject> findAllBills() {
        return billService.findAll();
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<ResponseObject> findAllBillsByUserId(@PathVariable("id") Long userId) {
        return billService.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<ResponseObject> findBillById(@PathVariable("id") Long id) {
        return billService.findById(id);
    }
}
