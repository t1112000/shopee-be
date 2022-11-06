package com.shopee.service;

import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface RevenueService {
    ResponseEntity<ResponseObject> getRevenue();
}
