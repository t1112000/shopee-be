package com.shopee.controller;

import com.shopee.dto.CartDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    private ResponseEntity<ResponseObject> getAllCarts() {
        return cartService.findAll();
    }


    @GetMapping("/{id}")
    private ResponseEntity<ResponseObject> getCartById(@PathVariable("id") Long id) {
        return cartService.findById(id);
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<ResponseObject> getCartByUserId(@PathVariable("id") Long userId) {
        return cartService.findByUserId(userId);
    }

    @PostMapping
    private ResponseEntity<ResponseObject> addToCart(@RequestBody @Valid CartDto cartDto) {
        return cartService.save(cartDto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ResponseObject> updateCart(@PathVariable("id") Long id, @RequestBody @Valid CartDto cartDto) {
        return cartService.update(id, cartDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseObject> deleteCartById(@PathVariable("id") Long id) {
        return cartService.delete(id);
    }
}
