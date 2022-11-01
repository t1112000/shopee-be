package com.shopee.service.impl;

import com.shopee.entity.BillEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.repository.BillRepository;
import com.shopee.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query bills successfully", billRepository.findAll()));
    }

    @Override
    public ResponseEntity<ResponseObject> findAllByUserId(Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query bills successfully", billRepository.findAllByUserId(userId)));
    }

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<BillEntity> foundBill = billRepository.findById(id);

        if (foundBill.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query bill successfully", foundBill));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find bill with id=" + id));
    }
}
