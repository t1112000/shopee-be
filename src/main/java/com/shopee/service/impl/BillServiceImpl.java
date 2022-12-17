package com.shopee.service.impl;

import com.shopee.dto.list.BillListDto;
import com.shopee.entity.BillEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.repository.BillRepository;
import com.shopee.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll(int page, int pageSize) {
        Pageable paging = PageRequest.of((page - 1), pageSize);

        List<BillEntity> bills = billRepository.findAll(paging).getContent();
        int total = billRepository.findAll().size();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query bills successfully", new BillListDto(total, bills)));
    }

    @Override
    public ResponseEntity<ResponseObject> findAllByUserId(int page, int pageSize, Long userId) {
        Pageable paging = PageRequest.of((page - 1), pageSize);

        List<BillEntity> bills = billRepository.findAllByUserId(paging, userId).getContent();
        int total = billRepository.findAll().size();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query bills successfully", new BillListDto(total, bills)));
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
