package com.shopee.service.impl;

import com.shopee.dto.CartDto;
import com.shopee.dto.CartResponseDto;
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
        Optional<UserEntity> foundUser = userRepository.findByIdAndIs_deletedFalse(userId);

        CartEntity cart = new CartEntity();
        CartResponseDto cartResponse = new CartResponseDto();

        if (foundUser.isPresent()) {
            cart.setUser(foundUser.get());
            cartResponse.setUser(foundUser.get());
        }

        if (foundCart.isPresent()) {
            cartResponse.setId(foundCart.get().getId());
            cartResponse.setCart_items(foundCart.get().getCart_items());
            cartResponse.setIs_deleted(foundCart.get().getIs_deleted());

            int total = 0;
            int quantity = 0;

            for (CartItemEntity cartItem : foundCart.get().getCart_items()) {
                if (!cartItem.getProduct().getIs_deleted()) {
                    total += cartItem.getQuantity() * cartItem.getProduct().getPrice();
                    quantity += cartItem.getQuantity();
                }
            }


            cartResponse.setTotal(total);
            cartResponse.setQuantity(quantity);
        } else {
            CartEntity cartSaved = cartRepository.save(cart);
            cartResponse.setId(cartSaved.getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Query cart successfully", cartResponse));
    }

    @Override
    public ResponseEntity<ResponseObject> save(CartDto cartDto) {
        Optional<ProductEntity> foundProduct = productRepository.findByIdAndIs_deletedFalse(cartDto.getProduct_id());
        Optional<UserEntity> foundUser = userRepository.findByIdAndIs_deletedFalse(cartDto.getUser_id());

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

        if (cartDto.getQuantity() == 0) {
            List<CartItemEntity> cartItems = cartItemRepository.findAllByProductId(cartDto.getProduct_id());

            List<Long> ids = new ArrayList<>();

            cartItems.forEach(cartItem -> {
                ids.add(cartItem.getId());
            });

            foundCart.map(cart -> {
                List<CartItemEntity> cartItemsByCart = new ArrayList<>();

                for (CartItemEntity cartItem : cart.getCart_items()) {
                    if (!ids.contains(cartItem.getId())) {
                        cartItemsByCart.add(cartItem);
                    }
                }

                cart.setCart_items(cartItemsByCart);
                return cartRepository.save(cart);
            });
            System.out.println(ids);
            cartItemRepository.deleteAllById(ids);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true, "Updated cart successfully", foundCart));
        }

        foundCart.map(cart -> {
            boolean is_product = false;
            List<CartItemEntity> cartItems = new ArrayList<>();

            for (CartItemEntity cartItem : cart.getCart_items()) {
                if (cartItem.getProduct().getId() == cartDto.getProduct_id()) {
                    cartItem.setQuantity(cartDto.getQuantity());
                    is_product = true;
                    cartItemRepository.save(cartItem);
                }

                cartItems.add(cartItem);
            }

            if (!is_product) {
                CartItemEntity cartItem = new CartItemEntity();
                cartItem.setQuantity(cartDto.getQuantity());
                cartItem.setProduct(foundProduct.get());

                cartItems.add(cartItemRepository.save(cartItem));
            }

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
