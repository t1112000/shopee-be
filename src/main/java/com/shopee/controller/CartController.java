package com.shopee.controller;

import com.shopee.dto.CartDto;
import com.shopee.entity.ResponseObject;
import com.shopee.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/carts")
@Tag(name = "Carts")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> getAllCarts(
            @RequestParam(defaultValue = "1") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(0) int pageSize
    ) {
        return cartService.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> getCartById(@PathVariable("id") Long id) {
        return cartService.findById(id);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<ResponseObject> getCartByUserId(@PathVariable("user_id") Long userId) {
        return cartService.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addToCart(@RequestBody @Valid CartDto cartDto) {
        return cartService.save(cartDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCart(@PathVariable("id") Long id, @RequestBody @Valid CartDto cartDto) {
        return cartService.update(id, cartDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> deleteCartById(@PathVariable("id") Long id) {
        return cartService.delete(id);
    }
}
