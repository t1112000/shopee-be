package com.shopee.controller;

import com.shopee.dto.PaymentDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    private ResponseEntity<ResponseObject> payment(@RequestBody @Valid PaymentDto paymentDto) {
        return paymentService.payment(paymentDto);
    }
}
