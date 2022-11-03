package com.shopee.service.impl;

import com.shopee.dto.PaymentDto;
import com.shopee.entity.BillEntity;
import com.shopee.entity.CartEntity;
import com.shopee.entity.CartItemEntity;
import com.shopee.entity.ResponseObject;
import com.shopee.repository.BillRepository;
import com.shopee.repository.CartRepository;
import com.shopee.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public ResponseEntity<ResponseObject> payment(PaymentDto paymentDto) {
        Optional<CartEntity> foundCart = cartRepository.findByIdAndIs_deletedFalse(paymentDto.getCart_id());

        if (foundCart.isPresent()) {
            BillEntity newBill = new BillEntity();

            int total = 0;
            int quantity = 0;

            List<CartItemEntity> cartItems = new ArrayList<>();

            for (CartItemEntity cartItem : foundCart.get().getCart_items()) {
                if (!cartItem.getProduct().getIs_deleted()) {
                    total += cartItem.getQuantity() * cartItem.getProduct().getPrice();
                    quantity += cartItem.getQuantity();
                    cartItems.add(cartItem);
                }
            }

            newBill.setTotal(total);
            newBill.setQuantity(quantity);
            newBill.setUser(foundCart.get().getUser());
            newBill.setCart_items(cartItems);

            foundCart.get().setIs_deleted(true);
            cartRepository.save(foundCart.get());

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Payment is sucessfully", billRepository.save(newBill)));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find cart with id=" + paymentDto.getCart_id()));
    }
}
