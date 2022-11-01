package com.shopee.service;

import com.shopee.dto.PaymentDto;
import com.shopee.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<ResponseObject> payment(PaymentDto paymentDto);
}
