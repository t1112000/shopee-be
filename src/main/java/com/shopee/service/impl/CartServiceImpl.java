package com.shopee.service.impl;

import com.shopee.dto.CartDto;
import com.shopee.entity.*;
import com.shopee.repository.CartItemRepository;
import com.shopee.repository.CartRepository;
import com.shopee.repository.ProductRepository;
import com.shopee.repository.UserRepository;
import com.shopee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query carts successfully", cartRepository.findAllByIs_deletedFalse()));
    }

    @Override
    public ResponseEntity<ResponseObject> findById(Long id) {
        Optional<CartEntity> foundCart = cartRepository.findByIdAndIs_deletedFalse(id);

        if (foundCart.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query cart successfully", foundCart));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find cart with id=" + id));
    }

    @Override
    public ResponseEntity<ResponseObject> findByUserId(Long userId) {
        Optional<CartEntity> foundCart = cartRepository.findByIs_deletedFalseAndUserId(userId);
        Optional<UserEntity> foundUser = userRepository.findById(userId);

        CartEntity cart = new CartEntity();

        if (foundUser.isPresent()) {
            cart.setUser(foundUser.get());
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query cart successfully", foundCart.isPresent() ? foundCart : cartRepository.save(cart)));
    }

    @Override
    public ResponseEntity<ResponseObject> save(CartDto cartDto) {
        Optional<ProductEntity> foundProduct = productRepository.findByIdAndIs_deletedFalse(cartDto.getProduct_id());
        Optional<UserEntity> foundUser = userRepository.findById(cartDto.getUser_id());

        if (foundProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find product with id=" + cartDto.getProduct_id()));
        }

        Optional<CartEntity> foundCart = cartRepository.findByIs_deletedFalseAndUserId(cartDto.getUser_id()).map(cart -> {
            List<CartItemEntity> cartItems = new ArrayList<>();

            boolean is_product = false;

            for (CartItemEntity cartItem : cart.getCart_items()) {
                if (cartItem.getProduct().getId() == cartDto.getProduct_id()) {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    is_product = true;
                    cartItemRepository.save(cartItem);
                }
                cartItems.add(cartItem);
            }

            if (!is_product) {
                CartItemEntity cartItem = new CartItemEntity();
                cartItem.setProduct(foundProduct.get());
                cartItems.add(cartItemRepository.save(cartItem));
            }

            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotal(cart.getTotal() + foundProduct.get().getPrice());
            cart.setCart_items(cartItems);

            return cartRepository.save(cart);
        });

        if (foundCart.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Updated cart successfully", foundCart));
        }

        CartEntity cart = new CartEntity();
        cart.setUser(foundUser.get());

        List<CartItemEntity> cartItems = new ArrayList<>();
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setProduct(foundProduct.get());
        cartItems.add(cartItemRepository.save(cartItem));

        cart.setQuantity(cart.getQuantity() + 1);
        cart.setTotal(cart.getTotal() + foundProduct.get().getPrice());
        cart.setCart_items(cartItems);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Add cart successfully", cartRepository.save(cart)));
    }

    @Override
    public ResponseEntity<ResponseObject> update(Long id, CartDto cartDto) {
        Optional<CartEntity> foundCart = cartRepository.findByIdAndIs_deletedFalse(id);
        Optional<ProductEntity> foundProduct = productRepository.findByIdAndIs_deletedFalse(cartDto.getProduct_id());

        if (foundCart.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find cart with id=" + id));
        }

        if (foundProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find product with id=" + cartDto.getProduct_id()));
        }

        foundCart.map(cart -> {
            int cartQuantity = 0;
            double totalMoney = 0;
            boolean is_product = false;
            List<CartItemEntity> cartItems = new ArrayList<>();

            for (CartItemEntity cartItem : cart.getCart_items()) {
                if (cartItem.getProduct().getId() == cartDto.getProduct_id()) {
                    cartItem.setQuantity(cartDto.getQuantity());
                    is_product = true;
                    cartItemRepository.save(cartItem);
                }

                cartQuantity += cartItem.getQuantity();
                totalMoney += cartItem.getQuantity() * cartItem.getProduct().getPrice();
                cartItems.add(cartItem);
            }

            if (!is_product) {
                CartItemEntity cartItem = new CartItemEntity();
                cartItem.setProduct(foundProduct.get());

                totalMoney += cartItem.getQuantity() * cartItem.getProduct().getPrice();
                cartQuantity += cartItem.getQuantity();
                cartItems.add(cartItemRepository.save(cartItem));
            }

            cart.setQuantity(cartQuantity);
            cart.setTotal(totalMoney);
            cart.setCart_items(cartItems);
            return cartRepository.save(cart);
        });

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Updated cart successfully", foundCart));
    }

    @Override
    public ResponseEntity<ResponseObject> delete(Long id) {
        Optional<CartEntity> foundCart = cartRepository.findById(id).map(cart -> {
            cart.setIs_deleted(true);

            return cartRepository.save(cart);
        });

        if (foundCart.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Deleted cart successfully"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Cannot find cart with id=" + id));
    }
}
