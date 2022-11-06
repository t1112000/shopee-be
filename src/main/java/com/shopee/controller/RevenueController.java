package com.shopee.controller;

import com.shopee.entity.ResponseObject;
import com.shopee.service.RevenueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revenues")
@Tag(name = "Revenues")
public class RevenueController {
    @Autowired
    private RevenueService revenueService;

    @GetMapping
    public ResponseEntity<ResponseObject> getRevenue() {
        return revenueService.getRevenue();
    }
}
