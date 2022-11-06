package com.shopee.service.impl;

import com.shopee.entity.BillEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.repository.BillRepository;
import com.shopee.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public ResponseEntity<ResponseObject> getRevenue() {
        List<BillEntity> bills = billRepository.findAll();
        int total = 0;

        for (BillEntity bill : bills) {
            total += bill.getTotal();
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query revenue successfully", total));
    }
}
